package com.example.falconeai.presentation.Uiviews

import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.falconeai.data.models.planets
import com.example.falconeai.data.models.vehicles

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun DetailCard(
    title: String,
    vhlist: List<vehicles>,
    dst: Int
) {
    var observer by remember {
        mutableStateOf("")
    }
    var expandedState by remember { mutableStateOf(false) }
    val rotation by animateFloatAsState(
        targetValue = if (expandedState) 180f else 0f
    )
    Card(modifier = Modifier
        .padding(1.dp)
        .clickable {
            expandedState = !expandedState
        }
        .animateContentSize(
            tween(
                durationMillis = 350,
                easing = LinearOutSlowInEasing
            )
        )
        .fillMaxWidth(),
        shape = RoundedCornerShape(15.dp),
        border = BorderStroke(1.dp, Color.Black.copy(0.4f)),
        elevation = 12.dp,
        backgroundColor = Color.Gray.copy(0.3f)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(5.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = title, modifier = Modifier
                        .padding(5.dp)
                        .weight(5f),
                    fontSize = 24.sp,
                    fontFamily = FontFamily.SansSerif,
                    fontStyle = FontStyle.Normal
                )
                IconButton(
                    onClick = { expandedState = !expandedState },
                    modifier = Modifier
                        .rotate(rotation)
                        .size(24.dp)
                        .weight(1f)
                ) {
                    Icon(
                        Icons.Default.ArrowDropDown,
                        contentDescription = null,
                    )
                }
            }
            AnimatedVisibility(
                visible = expandedState,
                modifier = Modifier.padding(5.dp)
            ) {
                Column {
                    vhlist.forEachIndexed { index, vehicles ->
                        Row {

                            RadioButton(
                                selected = observer == vehicles.name,
                                onClick = {
                                    observer = vehicles.name
                                    Log.i("Main", "Selected Value =${observer} --> ${title}")
                                }, enabled = vehicles.max_distance >= dst,
                                colors = RadioButtonDefaults.colors(
                                    selectedColor = Color.Magenta.copy(
                                        0.6f
                                    )
                                )
                            )
                            Spacer(modifier = Modifier.padding(2.dp))
                            Text(
                                text = vehicles.name,
                                fontFamily = FontFamily.SansSerif,
                                fontSize = 18.sp
                            )
                            Spacer(modifier = Modifier.padding(2.dp))
                            Text(
                                text = vehicles.max_distance.toString(),
                                fontFamily = FontFamily.SansSerif,
                                fontSize = 18.sp
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun DestinationCard(
    title: String,
    planet : List<planets>,
    vehicle:List<vehicles>
) {
    var expandedState by remember { mutableStateOf(false) }
    val rotation by animateFloatAsState(
        targetValue = if (expandedState) 180f else 0f
    )
    Card(
        modifier = Modifier
            .padding(1.dp)
            .clickable {
                expandedState = !expandedState
            }
            .animateContentSize(
                tween(
                    durationMillis = 350,
                    easing = LinearOutSlowInEasing
                )
            )
            .fillMaxWidth(),
        shape = RoundedCornerShape(15.dp),
        border = BorderStroke(1.dp, Color.Black.copy(0.4f)),
        elevation = 12.dp,
        backgroundColor = Color.Gray.copy(0.3f)
    ) {
        Column(modifier = Modifier.fillMaxSize()) {

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = title, modifier = Modifier
                        .padding(5.dp)
                        .weight(5f),
                    fontSize = 24.sp,
                    fontFamily = FontFamily.SansSerif,
                    fontStyle = FontStyle.Normal
                )
                IconButton(
                    onClick = { expandedState = !expandedState },
                    modifier = Modifier
                        .rotate(rotation)
                        .size(24.dp)
                        .weight(1f)
                ) {
                    Icon(
                        Icons.Default.ArrowDropDown,
                        contentDescription = null,
                    )
                }
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {

                Divider(thickness = 1.dp, color = Color.Black.copy(0.3f))
            }
        }
    }
}