package com.sideki.imdb_app.ui.movie_info.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import coil.compose.AsyncImage
import com.sideki.imdb_app.R.drawable
import com.sideki.imdb_app.domain.model.MovieInfoModel.ActorModel
import com.sideki.imdb_app.util.debugPlaceholder

@Preview
@Composable
fun ActorsBlock(
    @PreviewParameter(ActorPreviewProvider::class, 1) actors: List<ActorModel>,
    modifier: Modifier = Modifier
) {
    ConstraintLayout(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
        val titleRef = createRef()
        val sizeRef = createRef()
        val iconRef = createRef()
        val listRef = createRef()

        Text(
            text = "Актеры",
            modifier = Modifier
                .constrainAs(titleRef) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                }
                .padding(8.dp),
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )

        Text(
            text = "${actors.size}",
            modifier = Modifier
                .constrainAs(sizeRef) {
                    top.linkTo(iconRef.top)
                    end.linkTo(iconRef.start)
                    bottom.linkTo(iconRef.bottom)
                },
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )

        Icon(
            Icons.Filled.KeyboardArrowRight,
            contentDescription = "Next",
            tint = Color.White,
            modifier = Modifier.constrainAs(iconRef) {
                top.linkTo(titleRef.top)
                end.linkTo(parent.end)
                bottom.linkTo(titleRef.bottom)
            })

        LazyHorizontalGrid(
            rows = GridCells.Fixed(3),
            contentPadding = PaddingValues(bottom = 8.dp),
            modifier = Modifier
                .constrainAs(listRef) {
                    top.linkTo(titleRef.bottom)
                    start.linkTo(parent.start)
                }
                .height(200.dp)
                .padding(start = 8.dp)
        ) {
            items(actors) { item ->
                Actor(item)
            }
        }
    }
}

@Preview
@Composable
fun Actor(actor: ActorModel = ActorModel()) {
    Row(
        modifier = Modifier
            .wrapContentSize()
            .padding(end = 8.dp)
            .background(Color.Black)
    ) {
        AsyncImage(
            model = actor.image,
            contentDescription = "Actor photo",
            placeholder = debugPlaceholder(debugPreview = drawable.ic_launcher_background),
            modifier = Modifier
                .width(40.dp)
                .height(60.dp),
            contentScale = ContentScale.Crop
        )

        Column(
            modifier = Modifier
                .wrapContentSize()
                .padding(start = 8.dp, end = 8.dp)
        ) {
            Text(text = actor.name, color = Color.White, fontSize = 12.sp)
            Text(text = actor.asCharacter, color = Color.Gray, fontSize = 8.sp)
        }
    }
}

class ActorPreviewProvider : PreviewParameterProvider<List<ActorModel>> {
    override val values = sequenceOf(
        listOf(
            ActorModel(),
            ActorModel(),
            ActorModel(),
            ActorModel(),
            ActorModel(),
            ActorModel(),
            ActorModel(),
            ActorModel(),
            ActorModel()
        )
    )
}
