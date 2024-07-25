package com.codingambitions.socialmediafeedjc

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout


@Composable
fun SocialMediaPost() {

    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp)
    ) {

        val (avatar, name, username, moreOptions, image, like, comment, share, bookmark, likes, caption, comments, time) = createRefs()

        //Avatar
        Image(painter = painterResource(id = R.drawable.dummy_avatar),
            contentDescription = null,
            modifier = Modifier
                .padding(
                    start = 16.dp
                )
                .size(44.dp)
                .clip(CircleShape)
                .constrainAs(avatar) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                })

        //Name
        Text(
            text = "FirstName LastName",
            style = MaterialTheme.typography.titleSmall,
            modifier = Modifier.constrainAs(name) {
                top.linkTo(parent.top, margin = 3.dp)
                start.linkTo(avatar.end, margin = 8.dp)

            }
        )

        //UserName

        Text(
            text = "username",
            style = MaterialTheme.typography.titleSmall,
            color = Color.Gray,
            modifier = Modifier.constrainAs(username) {
                top.linkTo(name.bottom, margin = 3.dp)
                start.linkTo(avatar.end, margin = 8.dp)
            }
        )

        IconButton(onClick = { /*TODO*/ }, modifier = Modifier
            .padding(end = 16.dp)
            .size(24.dp)
            .constrainAs(moreOptions) {
                end.linkTo(parent.end)
                top.linkTo(avatar.top)
                bottom.linkTo(avatar.bottom)
            }
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_more_vertical),
                contentDescription = null
            )
        }


        Image(
            painter = painterResource(id = R.drawable.user1),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
                .constrainAs(image) {
                    top.linkTo(avatar.bottom, margin = 16.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
        )

        Icon(
            painter = painterResource(id = R.drawable.heart_icon),
            contentDescription = null,
            modifier = Modifier
                .size(24.dp)
                .constrainAs(like) {
                    top.linkTo(image.bottom, margin = 16.dp)
                    start.linkTo(parent.start, margin = 16.dp)
                }
        )

        Icon(
            painter = painterResource(id = R.drawable.comment_icon),
            contentDescription = null,
            modifier = Modifier
                .size(24.dp)
                .constrainAs(comment) {
                    top.linkTo(image.bottom, margin = 16.dp)
                    start.linkTo(like.end, margin = 16.dp)
                }
        )

        Icon(
            painter = painterResource(id = R.drawable.send_icon),
            contentDescription = null,
            modifier = Modifier
                .size(24.dp)
                .constrainAs(share) {
                    top.linkTo(image.bottom, margin = 16.dp)
                    start.linkTo(comment.end, margin = 16.dp)
                }
        )

        Icon(
            painter = painterResource(id = R.drawable.bookmark2),
            contentDescription = null,
            modifier = Modifier
                .size(24.dp)
                .constrainAs(bookmark) {
                    top.linkTo(image.bottom, margin = 16.dp)
                    end.linkTo(parent.end, margin = 16.dp)
                }
        )

        Text(
            text = stringResource(id = R.string.likes, "1,123"),
            style = MaterialTheme.typography.titleSmall,
            color = Color.Black,
            modifier = Modifier.constrainAs(likes) {
                top.linkTo(like.bottom, margin = 8.dp)
                start.linkTo(parent.start, margin = 16.dp)
            }
        )

        Text(
            text = "This is the caption of the post",
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier.constrainAs(caption) {
                top.linkTo(likes.bottom, margin = 4.dp)
                start.linkTo(parent.start, margin = 16.dp)
            }
        )

        Text(
            text = stringResource(id = R.string.comments, 13),
            style = MaterialTheme.typography.bodySmall,
            color = Color.Gray,
            modifier = Modifier.constrainAs(comments) {
                top.linkTo(caption.bottom, margin = 4.dp)
                start.linkTo(parent.start, margin = 16.dp)
            }
        )

        Text(
            text = "2 hours ago",
            style = MaterialTheme.typography.bodySmall,
            color = Color.Gray,
            modifier = Modifier.constrainAs(time) {
                top.linkTo(comments.bottom, margin = 4.dp)
                start.linkTo(parent.start, margin = 16.dp)
            }
        )


    }


}


//Preview
@Preview(showBackground = true)
@Composable
fun PreviewSocialMediaPost() {
    SocialMediaPost()
}