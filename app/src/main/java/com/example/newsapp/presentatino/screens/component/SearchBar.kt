package com.example.newsapp.presentatino.screens.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.newsapp.data.local.SearchHistory
import com.example.newsapp.presentatino.Dimension



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewSearchBar(
    modifier: Modifier = Modifier,
    onSearch:(String)-> Unit,
    history: List<SearchHistory>,
    onHistoryRemove:(Int)-> Unit,
) {
    var IsActive by remember { mutableStateOf(false) }
    var quety by remember { mutableStateOf("") }
    SearchBar(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = Dimension.SMALL_PADDING, bottom = Dimension.SMALL_PADDING),
        query = quety,
        onQueryChange = { quety = it },
        onSearch = {
            IsActive = false
            onSearch(quety)
        },
        active = IsActive,
        onActiveChange = {
            IsActive = !IsActive
        },
        placeholder = {Text("Enter the Keyword for Search ")},
        leadingIcon = {Icon(Icons.Default.Search, null)},

    ){
        history.forEach{
            HistoryCom(it, Modifier, {onSearch(it)}){ deleteString->onHistoryRemove(deleteString)}
        }
    }
}

@Composable
private fun HistoryCom(
    searchHistory: SearchHistory,
    modifier: Modifier = Modifier,
    onSearch:(String)-> Unit,
    onHistoryRemove: (Int) -> Unit
) {
    Row(
        modifier = Modifier.padding(horizontal = 18.dp).clickable{onSearch(searchHistory.value)},
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text =searchHistory.value,
            modifier = modifier.weight(1f),
            style = MaterialTheme.typography.headlineSmall
        )
        IconButton({onHistoryRemove(searchHistory.id)}) {
            Icon(
                Icons.Filled.Clear,
                null,
                modifier = modifier
                    .size(40.dp)
            )
        }
    }
}
