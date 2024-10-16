package com.yasunov.notesshiftlabyasunov.screen.mainScreen

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.yasunov.designsystem.component.ShiftButton
import com.yasunov.designsystem.component.ShiftScaffold
import com.yasunov.designsystem.theme.NotesShiftAppYasunov.colors
import com.yasunov.designsystem.theme.Typography
import com.yasunov.notesshiftlabyasunov.R
import com.yasunov.repository.model.NoteModel
import java.text.SimpleDateFormat
import com.yasunov.designsystem.R as RDesignSystem

@Composable
fun MainScreen(
    onNoteClicked: (id: Int) -> Unit,
    modifier: Modifier = Modifier
) {
    val viewModel: MainScreenViewModel = hiltViewModel()
    val uiState by viewModel.uiState.collectAsState()
    val dp0 = dimensionResource(id = RDesignSystem.dimen._0dp)
    val dp16 = dimensionResource(id = RDesignSystem.dimen._16dp)
    LaunchedEffect(viewModel) {
        viewModel.loadNotes()
    }
    var isDialogShown by remember {
        mutableStateOf(false)
    }
    AnimatedVisibility(visible = isDialogShown) {
        val dialogTitle = stringResource(R.string.title_deleting_note)
        val dialogText = stringResource(R.string.answer_to_delete_note)
        val no = stringResource(R.string.no)
        val yes = stringResource(R.string.yes)
        AlertDialog(
            onDismissRequest = { isDialogShown = false },
            onConfirmation = {
                isDialogShown = false
                viewModel.deleteItemById()
                viewModel.updateSelectedId(null)

            },
            dialogTitle = dialogTitle,
            dialogText = dialogText,
            textButton = Pair(no, yes),
        )
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
            is MainScreenUiState.NoNotes -> NoNotes()
            is MainScreenUiState.Loading -> Loading()
            is MainScreenUiState.Success -> Success(
                uiState = uiState,
                onNoteClicked = onNoteClicked,
                onLongPressItem = {
                    isDialogShown = true
                    viewModel.updateSelectedId(it)
                }
            )
        }
        AddNoteButton(onNoteClicked, dp16)

    }

}

@Composable
private fun AddNoteButton(
    onNoteClicked: (id: Int) -> Unit,
    dp16: Dp
) {
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

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun Success(
    uiState: MainScreenUiState,
    onNoteClicked: (id: Int) -> Unit,
    onLongPressItem: (Int) -> Unit,
) {
    val dp12 = dimensionResource(id = RDesignSystem.dimen._12dp)
    val dp16 = dimensionResource(id = RDesignSystem.dimen._16dp)
    val haptics = LocalHapticFeedback.current
    LazyColumn(
        modifier = Modifier
            .fillMaxSize(),
        contentPadding = PaddingValues(dp16),
        verticalArrangement = Arrangement.spacedBy(dp12)
    ) {
        items((uiState as MainScreenUiState.Success).list, key = { it.id!!.toInt() }) {
            Item(
                model = it,
                modifier = Modifier
                    .combinedClickable(
                        onClick = { onNoteClicked(it.id!!) },
                        onLongClick = {
                            haptics.performHapticFeedback(HapticFeedbackType.LongPress)
                            onLongPressItem(it.id!!)
                        },
                    )
            )

        }

    }
}

@Composable
private fun Loading() {
    val dp4 = dimensionResource(id = RDesignSystem.dimen._4dp)
    val dp64 = dimensionResource(id = RDesignSystem.dimen._64dp)
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

@Composable
private fun NoNotes() {
    val dp12 = dimensionResource(id = RDesignSystem.dimen._12dp)
    val dp256 = dimensionResource(id = RDesignSystem.dimen._256dp)
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

@SuppressLint("SimpleDateFormat")
@Composable
fun Item(
    model: NoteModel,
    modifier: Modifier = Modifier
) {
    val dp4 = dimensionResource(id = RDesignSystem.dimen._4dp)
    val dp12 = dimensionResource(id = RDesignSystem.dimen._12dp)
    val dp16 = dimensionResource(id = RDesignSystem.dimen._16dp)
    val sdf = SimpleDateFormat("yyyy-MM-dd")
    val formattedDate = java.util.Date(model.dateOfCreation)
    Card(
        shape = RoundedCornerShape(dp16),
        backgroundColor = Color.White,
        contentColor = colors.bodyPrimaryText,
        border = null,
        elevation = dp4,
        modifier = Modifier
            .fillMaxWidth()
            .then(modifier)
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(dp12),
            horizontalAlignment = Alignment.Start,
            modifier = Modifier.padding(horizontal = dp16, vertical = dp12)
        ) {

            Text(
                text = model.title.take(60),
                textAlign = TextAlign.Left,
                color = colors.titleText,
                maxLines = 1,
                style = Typography.h6,
            )
            Text(
                text = if (model.body.length > 120) {
                    model.body.take(120) + "..."
                } else {
                    model.body
                },
                fontSize = 14.sp,
                color = colors.light,
                maxLines = 3,
                textAlign = TextAlign.Left,
                style = Typography.body1,
            )
            Text(
                text = sdf.format(formattedDate).toString(),
                fontSize = 12.sp,
                color = colors.light,
                maxLines = 1,
                textAlign = TextAlign.Left,
                style = Typography.body1,
            )


        }
    }

}
