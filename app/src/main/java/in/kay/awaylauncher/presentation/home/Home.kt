package `in`.kay.awaylauncher.presentation.home

import `in`.kay.awaylauncher.presentation.HomeViewModel
import `in`.kay.awaylauncher.ui.theme.Typography
import `in`.kay.awaylauncher.ui.theme.colorBackground
import `in`.kay.awaylauncher.ui.theme.colorWhite
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.*
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
import androidx.hilt.navigation.compose.hiltViewModel

@Preview
@Composable
fun HomeScreen(viewModel: HomeViewModel = hiltViewModel()) {
    var currentTime by remember {
        mutableStateOf("")
    }
    var currentAmPm by remember {
        mutableStateOf("")
    }
    var currentDate by remember {
        mutableStateOf("")
    }
    LaunchedEffect(key1 = true, block = {
        viewModel.getTimeFlow().collect {
            currentTime = it.first
            currentAmPm = it.second
        }
    })
    Column() {
        BoxWithConstraints() {
            ConstraintLayout(
                decoupledConstraints(24.dp), modifier = Modifier
                    .fillMaxSize()
                    .background(colorBackground)
                    .padding(16.dp)
            ) {
                Row(modifier = Modifier.layoutId("tvTime")) {
                    Text(
                        text = currentTime,
                        style = Typography.h1,
                        fontSize = 32.sp
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Text(
                        text = currentAmPm.uppercase(),
                        style = Typography.h1,
                        fontSize = 16.sp
                    )
                }
                Text(
                    text = "Saturday, 17th September",
                    style = Typography.body1,
                    color = colorWhite.copy(alpha = 0.5f),
                    modifier = Modifier.layoutId("tvDate")
                )
                Row(
                    modifier = Modifier
                        .layoutId("headerCard")
                        .clip(RoundedCornerShape(32.dp))
                        .border(1.dp, colorWhite, RoundedCornerShape(32.dp))
                        .padding(6.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painter = painterResource(id = `in`.kay.awaylauncher.R.drawable.ic_launcher_background),
                        contentDescription = "icon",
                        tint = colorWhite,
                        modifier = Modifier
                            .size(26.dp)
                            .clip(RoundedCornerShape(32.dp))
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
            start.linkTo(parent.start, margin = 24.dp)
        }
        constrain(tvDate) {
            top.linkTo(tvTime.bottom, 8.dp)
            start.linkTo(parent.start, margin = 24.dp)
        }
        constrain(headerCard) {
            top.linkTo(tvDate.bottom, 24.dp)
            start.linkTo(parent.start, margin = 16.dp)
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
            bottom.linkTo(parent.bottom, margin = 24.dp)
            end.linkTo(parent.end, 24.dp)
        }
    }
}