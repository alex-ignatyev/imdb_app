package com.sideki.imdb_app.ui.movie_info.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons.Filled
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
import com.sideki.imdb_app.domain.model.MovieInfoModel.SimilarMovieModel
import com.sideki.imdb_app.util.debugPlaceholder

@Preview
@Composable
fun SimilarList(
    @PreviewParameter(SimilarPreviewProvider::class, 1) similars: List<SimilarMovieModel>,
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
            text = "Похожие фильмы",
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
            text = "${similars.size}",
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
            Filled.KeyboardArrowRight,
            contentDescription = "Next",
            tint = Color.White,
            modifier = Modifier.constrainAs(iconRef) {
                top.linkTo(titleRef.top)
                end.linkTo(parent.end)
                bottom.linkTo(titleRef.bottom)
            })

        LazyRow(
            contentPadding = PaddingValues(bottom = 8.dp),
            modifier = Modifier
                .constrainAs(listRef) {
                    top.linkTo(titleRef.bottom)
                    start.linkTo(parent.start)
                }
                .wrapContentHeight()
                .padding(start = 8.dp)
        ) {
            items(similars) { item ->
                Similar(item)
            }
        }
    }
}

@Preview
@Composable
fun Similar(similar: SimilarMovieModel = SimilarMovieModel()) {
    Column(
        modifier = Modifier
            .wrapContentSize()
            .padding(start = 8.dp, end = 8.dp)
    ) {
        AsyncImage(
            model = similar.image,
            contentDescription = "Similar image",
            placeholder = debugPlaceholder(debugPreview = drawable.ic_launcher_background),
            modifier = Modifier
                .width(80.dp)
                .height(120.dp),
            contentScale = ContentScale.Crop
        )

        Text(text = similar.title, color = Color.White, fontSize = 8.sp, modifier = Modifier.width(80.dp))

        //TODO Добавить рейтинг
    }
}

class SimilarPreviewProvider : PreviewParameterProvider<List<SimilarMovieModel>> {
    override val values = sequenceOf(
        listOf(
            SimilarMovieModel(),
            SimilarMovieModel(),
            SimilarMovieModel(),
            SimilarMovieModel(),
            SimilarMovieModel(),
            SimilarMovieModel(),
            SimilarMovieModel(),
            SimilarMovieModel(),
            SimilarMovieModel()
        )
    )
}