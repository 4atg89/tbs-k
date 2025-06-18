package com.atg.tbs.ui.events.details

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.koinScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.atg.tbs.base.view.GoBack
import com.atg.tbs.domain.events.model.EntryFeeEntity
import com.atg.tbs.domain.events.model.EntryFeeType
import com.atg.tbs.domain.events.model.EventEntity
import org.koin.core.component.KoinComponent
import org.koin.core.parameter.parametersOf

internal class EventDetailsScreen(private val event: EventEntity) : Screen, KoinComponent {

    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val screenModel = koinScreenModel<EventDetailsScreenModel>(parameters = { parametersOf(event) })
        val props by screenModel.props

        Column {
            GoBack { navigator.pop() }
            props.event?.let { eventEntity ->
                ClassicChallengeScreen(
                    title = eventEntity.title,
                    description = eventEntity.description,
                    winCount = eventEntity.winCount,
                    lossCount = eventEntity.lossCount,
                    gold = eventEntity.reward.gold,
                    pogs = eventEntity.reward.pogs,
                    entryFees = eventEntity.entryFees,
                    onJoinEvent = props.joinEvent
                )
            } ?: run {

                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text("Loading event details...")
                }
            }
        }
    }
}

@Composable
fun ClassicChallengeScreen(
    title: String,
    description: String,
    winCount: Int,
    lossCount: Int,
    gold: Int,
    pogs: Int,
    entryFees: List<EntryFeeEntity>,
    onJoinEvent: (EntryFeeType) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF1E90FF))
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = title,
                style = MaterialTheme.typography.h5.copy(fontWeight = FontWeight.Bold),
                color = Color.White,
                modifier = Modifier.weight(1f),
                textAlign = TextAlign.Center
            )
            Icon(
                imageVector = Icons.Default.Info,
                contentDescription = "Info",
                tint = Color.Yellow,
                modifier = Modifier.size(28.dp)
            )
        }
        Spacer(modifier = Modifier.height(16.dp))

        Box(
            modifier = Modifier
                .size(90.dp)
                .background(Color(0xFF388E3C), shape = CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Text("⚔️", fontSize = 40.sp, textAlign = TextAlign.Center)
        }
        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = description,
            style = MaterialTheme.typography.body1,
            color = Color.White,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(24.dp))

        ProgressionGrid(
            modifier = Modifier.fillMaxWidth(),
            totalSteps = winCount + lossCount,
            currentStep = winCount
        )
        
        Spacer(modifier = Modifier.height(4.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text("Wins: 0 out of $winCount", color = Color(0xFF00FF00), fontSize = 14.sp)
            Text("Loses: $lossCount", color = Color(0xFFFF4444), fontSize = 14.sp)
        }
        Spacer(modifier = Modifier.height(24.dp))

        Card(
            modifier = Modifier.fillMaxWidth(0.7f),
            shape = RoundedCornerShape(12.dp),
            elevation = 6.dp
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text("Prize", fontWeight = FontWeight.Bold, fontSize = 16.sp)
                Spacer(modifier = Modifier.height(8.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text("\uD83D\uDCB0 $gold", fontSize = 16.sp)
                    Spacer(modifier = Modifier.width(16.dp))
                    Text("\uD83D\uDCC4 $pogs", fontSize = 16.sp)
                }
            }
        }
        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = { onJoinEvent.invoke(entryFees.first().type) },
            colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF43A047)),
            modifier = Modifier.fillMaxWidth(0.7f)
        ) {
            Text("Participate  ${entryFees.joinToString { "${it.feeCount} | ${it.type}" }} \uD83D\uDC8E", color = Color.White, fontSize = 16.sp)
        }
        Spacer(modifier = Modifier.height(8.dp))
        Button(
            onClick = {},
            colors = ButtonDefaults.buttonColors(backgroundColor = Color.LightGray),
            modifier = Modifier.fillMaxWidth(0.7f)
        ) {
            Text("Participate", color = Color.DarkGray, fontSize = 16.sp)
        }
    }
}

@Composable
private fun ProgressionGrid(
    modifier: Modifier = Modifier,
    totalSteps: Int,
    currentStep: Int
) {
    val stepsPerRow = 3
    val rows = (totalSteps + stepsPerRow - 1) / stepsPerRow

    Column(
        modifier = modifier.padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        repeat(rows) { row ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                repeat(stepsPerRow) { col ->
                    val isReversedRow = row % 2 == 1
                    val adjustedCol = if (isReversedRow) stepsPerRow - 1 - col else col
                    val stepNumber = row * stepsPerRow + adjustedCol
                    
                    if (stepNumber < totalSteps) {
                        val isActive = stepNumber < currentStep
                        val nextStepNumber = stepNumber + 1
                        val hasNextStep = nextStepNumber < totalSteps
                        
                        Box(modifier = Modifier.weight(1f)) {
                            ProgressionStep(
                                number = (stepNumber + 1) * 100,
                                isActive = isActive
                            )
                            
                            if (hasNextStep) {
                                val nextRow = nextStepNumber / stepsPerRow
                                val nextCol = nextStepNumber % stepsPerRow
                                val isCurrentReversed = (stepNumber / stepsPerRow) % 2 == 1
                                val isNextReversed = nextRow % 2 == 1
                                
                                // Calculate arrow direction
                                val arrowDirection = when {
                                    nextRow > row -> "↓"  // Down arrow
                                    isCurrentReversed && col > 0 -> "←"  // Left arrow
                                    !isCurrentReversed && col < stepsPerRow - 1 -> "→"  // Right arrow
                                    else -> ""
                                }
                                
                                if (arrowDirection.isNotEmpty()) {
                                    Text(
                                        text = arrowDirection,
                                        color = if (isActive) Color(0xFFFFA726) else Color.Gray,
                                        modifier = Modifier.align(
                                            when (arrowDirection) {
                                                "→" -> Alignment.CenterEnd
                                                "←" -> Alignment.CenterStart
                                                else -> Alignment.BottomCenter
                                            }
                                        ),
                                        fontSize = 24.sp,
                                        fontWeight = FontWeight.Bold
                                    )
                                }
                            }
                        }
                    } else {
                        Spacer(modifier = Modifier.weight(1f))
                    }
                }
            }
        }
    }
}

@Composable
private fun ProgressionStep(
    number: Int,
    isActive: Boolean,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier.size(48.dp),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .size(48.dp)
                .background(
                    color = if (isActive) Color(0xFFFFA726) else Color.Gray.copy(alpha = 0.3f),
                    shape = CircleShape
                ),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = number.toString(),
                color = if (isActive) Color.White else Color.Gray,
                style = MaterialTheme.typography.body1.copy(
                    fontWeight = FontWeight.Bold
                )
            )
        }
    }
}