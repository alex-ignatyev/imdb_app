package com.sideki.imdb_app.ui.movie_info.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
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
import coil.compose.AsyncImage
import com.sideki.imdb_app.R.drawable
import com.sideki.imdb_app.model.model.MovieInfoModel.ImageModel
import com.sideki.imdb_app.util.debugPlaceholder

@Preview
@Composable
fun ImagesBlock(
    @PreviewParameter(ImagesPreviewProvider::class, 1) images: List<ImageModel>,
    modifier: Modifier = Modifier
) {
    Column(modifier) {
        Text(
            text = "Кадры",
            modifier = Modifier
                .padding(8.dp),
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )

        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
        ) {
            items(images) { item ->
                AsyncImage(
                    model = item.imageUrl,
                    placeholder = debugPlaceholder(debugPreview = drawable.ic_launcher_background),
                    contentDescription = "Movie image",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .width(80.dp)
                        .height(120.dp)
                        .padding(start = 8.dp, bottom = 8.dp)
                )
            }
        }
    }
}

class ImagesPreviewProvider : PreviewParameterProvider<List<ImageModel>> {
    override val values = sequenceOf(
        listOf(
            ImageModel(),
            ImageModel(),
            ImageModel(),
            ImageModel(),
            ImageModel()
        )
    )
}