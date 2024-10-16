package com.yasunov.notesshiftlabyasunov.screen.mainScreen

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.yasunov.designsystem.component.ShiftButton
import com.yasunov.designsystem.component.ShiftScaffold
import com.yasunov.designsystem.theme.NotesShiftAppYasunov
import com.yasunov.designsystem.theme.NotesShiftAppYasunov.colors
import com.yasunov.designsystem.theme.Typography
import com.yasunov.notesshiftlabyasunov.R
import com.yasunov.repository.model.NoteModel
import java.text.SimpleDateFormat
import java.util.Locale
import com.yasunov.designsystem.R as RDesignSystem

@Composable
fun MainScreen(
    onNoteClicked: (id: Int) -> Unit,
    modifier: Modifier = Modifier
) {
    val viewModel: MainScreenViewModel = hiltViewModel()
    val uiState by viewModel.uiState.collectAsState()

    val dp0 = dimensionResource(id = RDesignSystem.dimen._0dp)
    val dp4 = dimensionResource(id = RDesignSystem.dimen._4dp)

    val dp12 = dimensionResource(id = RDesignSystem.dimen._12dp)
    val dp16 = dimensionResource(id = RDesignSystem.dimen._16dp)
    val dp64 = dimensionResource(id = RDesignSystem.dimen._64dp)
    val dp256 = dimensionResource(id = RDesignSystem.dimen._256dp)
    LaunchedEffect(viewModel) {
        viewModel.loadNotes()
    }
    ShiftScaffold(
        topBar = {
            TopAppBar(
                title = {
                    val addNoteStringResource =
                        stringResource(R.string.app_title)
                    Text(text = addNoteStringResource, style = Typography.h6, color = Color.Gray)
                },
                backgroundColor = colors.uiBackground,
                contentColor = colors.brand,
                elevation = dp0,
                modifier = Modifier
                    .padding(top = dp16)
                    .then(modifier)
            )
        }
    ) { _ ->
        when (uiState) {
            is MainScreenUiState.NoNotes -> {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .fillMaxSize(),
                ) {
                    val image = painterResource(id = RDesignSystem.drawable.no_notes)
                    Image(
                        painter = image,
                        contentDescription = "No notes",
                        modifier = Modifier.size(dp256)
                    )
                    Text(
                        text = stringResource(R.string.add_notes),
                        style = Typography.body2,
                        color = Color.Black,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .width(dp256)
                            .padding(top = dp12, bottom = dp12)
                    )

                }
            }

            is MainScreenUiState.Loading -> {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .fillMaxSize(),
                ) {
                    CircularProgressIndicator(
                        color = colors.brand,
                        strokeWidth = dp4,
                        backgroundColor = Color.Transparent,
                        strokeCap = StrokeCap.Round,
                        modifier = Modifier
                            .size(dp64),
                    )
                }
            }

            is MainScreenUiState.Success -> {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize(),
                    contentPadding = PaddingValues(dp16),
                    verticalArrangement = Arrangement.spacedBy(dp12)
                ) {
                    items((uiState as MainScreenUiState.Success).list, key = { it.id!!.toInt() }) {
                        Item(model = it, onClickCard = {
                            onNoteClicked(it.id!!)
                        })
                    }

                }
            }
        }
        Column {
            Spacer(modifier = Modifier.weight(1f))
            ShiftButton(
                onClick = { onNoteClicked(-1) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = dp16, start = dp16, end = dp16)
            ) {
                Box(
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = stringResource(R.string.add_note),
                        style = Typography.button,
                        textAlign = TextAlign.Center,
                        color = Color.White,

                        )

                }
            }

        }

    }

}

@Composable
fun NoteItem(model: NoteModel) {
    val formattedDate =
        SimpleDateFormat("dd.MM.yyyy", Locale.getDefault()).format(model.dateOfCreation)
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(max = 32.dp),
        shape = RoundedCornerShape(8.dp),
        elevation = 0.dp,
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(text = model.title, fontSize = 18.sp, color = Color.Black)
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = formattedDate, fontSize = 12.sp, color = Color.Gray)
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = if (model.body.length > 30) {
                    model.body.take(30) + "..."
                } else {
                    model.body
                },
                fontSize = 14.sp,
                color = Color.DarkGray,
                maxLines = 3,
            )
        }
    }
}

@SuppressLint("SimpleDateFormat")
@Composable
fun Item(
    model: NoteModel,
    onClickCard: () -> Unit,
    modifier: Modifier = Modifier
) {
    val dp4 = dimensionResource(id = RDesignSystem.dimen._4dp)
    val dp8 = dimensionResource(id = RDesignSystem.dimen._8dp)
    val dp12 = dimensionResource(id = RDesignSystem.dimen._12dp)
    val dp16 = dimensionResource(id = RDesignSystem.dimen._16dp)
    val sdf = SimpleDateFormat("yyyy-MM-dd")
    val formattedDate = java.util.Date(1532358895L * 1000L)
//    val formattedDate =
//        SimpleDateFormat("dd.MM.yyyy", Locale.getDefault()).format(model.dateOfCreation)
    Card(
        shape = RoundedCornerShape(dp16),
        backgroundColor = Color.White,
        contentColor = colors.bodyPrimaryText,
        border = null,
        elevation = dp4,
        modifier = modifier
            .fillMaxWidth()
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null,
                onClick = onClickCard
            )
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(dp12),
            horizontalAlignment = Alignment.Start,
            modifier = Modifier.padding(horizontal = dp16, vertical = dp12)
        ) {

            Text(
                text = model.title,
                textAlign = TextAlign.Left,
                color = colors.titleText,
                maxLines = 1,
                style = Typography.h6,
            )
            Text(
                text = if (model.body.length > 30) {
                    model.body.take(30) + "..."
                } else {
                    model.body
                },
                fontSize = 14.sp,
                color = colors.light,
                maxLines = 3,
                textAlign = TextAlign.Left,
                style = Typography.body1,
//                modifier = Modifier.padding(bottom = dp8),
            )
            Text(
                text = sdf.format(formattedDate).toString(),
                fontSize = 12.sp,
                color = colors.light,
                maxLines = 1,
                textAlign = TextAlign.Left,
                style = Typography.body1,
//                modifier = Modifier.padding(bottom = dp8),
            )


        }
    }

}
