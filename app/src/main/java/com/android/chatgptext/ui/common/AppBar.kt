package com.android.chatgptext.ui.common

import android.widget.Space
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.android.chatgptext.ui.theme.BackGroundColor
import com.android.chatgptext.ui.theme.ChatGPTExtTheme
import com.android.chatgptext.utils.urlToAvatarGPT

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBar(onClickMenu: () -> Unit) {
    ChatGPTExtTheme {
        Surface(
            shadowElevation = 4.dp,
            tonalElevation = 0.dp
        ) {
            CenterAlignedTopAppBar(
                title = {
                    val paddingSizeModifier =
                        Modifier
                            .padding(start = 16.dp, top = 16.dp, bottom = 16.dp)
                            .size(32.dp)
                    Box {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Image(
                                painter = rememberAsyncImagePainter(urlToAvatarGPT),
                                contentDescription = null,
                                contentScale = ContentScale.Crop,
                                modifier = paddingSizeModifier.then(
                                    Modifier.clip(
                                        RoundedCornerShape(
                                            6.dp
                                        )
                                    )
                                )
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = "ChatGPT",
                                textAlign = TextAlign.Center,
                                fontSize = 16.5.sp,
                                fontWeight = FontWeight.SemiBold
                            )
                            Spacer(modifier = Modifier.width(12.dp))
                        }
                    }
                },
                navigationIcon = {
                    IconButton(
                        onClick = {
                            onClickMenu()
                        },
                    ) {
                        Icon(
                            Icons.Filled.Menu,
                            "backIcon",
                            modifier = Modifier.size(26.dp),
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                },
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = BackGroundColor,
                    titleContentColor = Color.White
                )
            )
        }
    }
}