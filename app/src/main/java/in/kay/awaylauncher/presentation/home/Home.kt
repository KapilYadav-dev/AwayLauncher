package `in`.kay.awaylauncher.presentation.home

import `in`.kay.awaylauncher.ui.theme.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet

@Preview
@Composable
fun HomeScreen() {
    Column() {
        BoxWithConstraints() {
            ConstraintLayout(
                decoupledConstraints(24.dp), modifier = Modifier
                    .fillMaxSize()
                    .background(colorBackground)
                    .padding(sixteenDp)
            ) {
                Text(
                    text = "12:04",
                    style = Typography.h1,
                    fontSize = 32.sp,
                    modifier = Modifier.layoutId("tvTime")
                )
                Text(
                    text = "Saturday, 17th September",
                    style = Typography.body1,
                    color = colorWhite.copy(alpha = 0.5f),
                    modifier = Modifier.layoutId("tvDate")
                )
                Row(
                    modifier = Modifier
                        .layoutId("headerCard")
                        .clip(RoundedCornerShape(thirtyTwoDp))
                        .border(1.dp, colorWhite, RoundedCornerShape(thirtyTwoDp))
                        .padding(6.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painter = painterResource(id = `in`.kay.awaylauncher.R.drawable.ic_launcher_background),
                        contentDescription = "icon",
                        tint = colorWhite,
                        modifier = Modifier
                            .size(26.dp)
                            .clip(RoundedCornerShape(thirtyTwoDp))
                    )
                    Text(
                        text = "Screen time : 9hr, 10mins",
                        style = Typography.body1,
                        color = colorWhite,
                        modifier = Modifier
                            .layoutId("tvDate")
                            .padding(start = 12.dp, end = 24.dp)
                    )
                }
                Text(
                    text = "Add your 5 favorites\napps here",
                    style = Typography.h1,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.layoutId("tvNoApps")
                )
                Text(
                    text = "Click here to\nadd apps",
                    style = Typography.h1,
                    fontSize = 16.sp,
                    color = colorWhite.copy(alpha = 0.4f),
                    fontWeight = FontWeight.SemiBold,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.layoutId("tvClickHere")
                )

                Image(
                    painter = painterResource(id = `in`.kay.awaylauncher.R.drawable.img_dir),
                    contentDescription = null,
                    contentScale = ContentScale.Fit,
                    modifier = Modifier
                        .layoutId("ivDirection")
                )
                Image(
                    painter = painterResource(id = `in`.kay.awaylauncher.R.drawable.ic_btnadd),
                    contentDescription = "ic_add",
                    modifier = Modifier
                        .layoutId("ivAddApps")
                )
            }
        }
    }
}

private fun decoupledConstraints(margin: Dp): ConstraintSet {
    return ConstraintSet {
        val tvTime = createRefFor("tvTime")
        val tvDate = createRefFor("tvDate")
        val tvNoApps = createRefFor("tvNoApps")
        val ivAddApps = createRefFor("ivAddApps")
        val ivDirection = createRefFor("ivDirection")
        val tvClickHere = createRefFor("tvClickHere")
        val headerCard = createRefFor("headerCard")
        val topAppsList = createRefFor("topAppsList")


        constrain(tvTime) {
            top.linkTo(parent.top, margin = margin)
            start.linkTo(parent.start, margin = startMarginStandard)
        }
        constrain(tvDate) {
            top.linkTo(tvTime.bottom, 8.dp)
            start.linkTo(parent.start, margin = startMarginStandard)
        }
        constrain(headerCard) {
            top.linkTo(tvDate.bottom, twentyFourDp)
            start.linkTo(parent.start, margin = sixteenDp)
        }
        constrain(tvNoApps) {
            top.linkTo(parent.top)
            bottom.linkTo(parent.bottom)
            centerHorizontallyTo(parent)
        }
        constrain(ivDirection) {
            bottom.linkTo(parent.bottom, margin = 0.dp)
            centerHorizontallyTo(parent)
        }
        constrain(tvClickHere) {
            bottom.linkTo(ivDirection.top, margin = (-56).dp)
            end.linkTo(ivDirection.start, (-36).dp)
        }
        constrain(ivAddApps) {
            bottom.linkTo(parent.bottom, margin = startMarginStandard)
            end.linkTo(parent.end, startMarginStandard)
        }
    }
}