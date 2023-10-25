package com.piappstudio.finmyip.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.piappstudio.finmyip.R
import com.piappstudio.finmyip.ui.PiProgressIndicator

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ConfigDetailScreen(viewModel: ConfigDetailViewModel = hiltViewModel()) {

    LaunchedEffect(Unit) {
        viewModel.fetchIpConfig()
    }

    val uiState by viewModel.uiState.collectAsState()

    Scaffold (topBar = {
        TopAppBar(title = {
            Text(stringResource(R.string.ip_details))
        })
    }) {
        if (uiState.isProgress) {
            PiProgressIndicator()
        }
        uiState.ipDetail?.let { ipConfig->
            Column (modifier = Modifier
                .padding(it)
                .padding(16.dp)){
                ipConfig.ip?.let {
                    DetailInfo(stringResource(R.string.ip), it)
                }
                ipConfig.city?.let {
                    DetailInfo(stringResource(R.string.city), it)
                }
                ipConfig.country?.let {
                    DetailInfo(stringResource(R.string.country), it)
                }
                ipConfig.asn?.let {
                    DetailInfo(stringResource(R.string.asn), it)
                }
            }

        }
    }




}
@Composable
fun DetailInfo(title:String, description:String) {
    Row (modifier = Modifier
        .fillMaxWidth()
        .padding(8.dp), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
        Text(title, style = MaterialTheme.typography.titleMedium)
        Text(description, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.ExtraBold)
    }
}

@Preview
@Composable
fun PreviewDetailInfo() {
    DetailInfo("IP Address", "107.141.2.34")
}
