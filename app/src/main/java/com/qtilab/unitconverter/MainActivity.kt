package com.qtilab.unitconverter

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.qtilab.unitconverter.ui.theme.UnitconverterTheme
import kotlin.math.roundToInt
import kotlin.math.roundToLong

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UnitconverterTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    unitConverter()
                }
            }
        }
    }
}

@Composable
fun unitConverter(){
    var inputvalue by remember { mutableStateOf("") }
    var outputvalue by remember {mutableStateOf("")}
    var inputunit by remember {mutableStateOf("centimeter")}
    var outputunit by remember {mutableStateOf("meter")}
    var inexpanded by remember { mutableStateOf(false)}
    var ouexpanded by remember { mutableStateOf(false)}
    var inconversionfactor by remember { mutableStateOf(1.00)}
    var ouconversionfactor by remember { mutableStateOf(1.00)}

    fun convertunit(){
        var inputtodouble = inputvalue.toDoubleOrNull() ?: 0.0
        outputvalue = ((((inputtodouble * inconversionfactor * 100)/ouconversionfactor))/100).toString()
    }
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
       Text("Unit Converter",style = MaterialTheme.typography.headlineLarge)
        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = inputvalue,
            onValueChange = {
            inputvalue = it
           },
            label = { Text("Enter value")})
        Spacer(modifier = Modifier.height(16.dp))
        Row {
           Box {
               //input box
             Button(onClick = { inexpanded = true }) {
                 Text(inputunit)
                 Icon(Icons.Default.ArrowDropDown,contentDescription = "select")
             }
               DropdownMenu(expanded = inexpanded, onDismissRequest = { inexpanded = false }) {
                   DropdownMenuItem(text = { Text("Centimeter")}, onClick = {
                       inputunit = "centimeter"
                       inexpanded = false
                       inconversionfactor = 0.01
                       inputunit = "Centimeter"
                       convertunit()
                   })
                   DropdownMenuItem(text = { Text("meter")}, onClick = {
                       inputunit = "meter"
                       inexpanded = false
                       inconversionfactor = 1.00
                       inputunit = "meter"
                       convertunit()
                   })
                   DropdownMenuItem(text = { Text("feet")}, onClick = {
                       inputunit = "feet"
                       inexpanded = false
                       inconversionfactor = 0.3048
                       inputunit = "feet"
                       convertunit()
                   })
                   DropdownMenuItem(text = { Text("millimeter")}, onClick = {
                       inputunit = "millimeter"
                       inexpanded = false
                       inconversionfactor = 0.001
                       inputunit = "millimeter"
                       convertunit()
                   })
               }
          }
            Spacer(modifier = Modifier.width(16.dp))
            Box {
                //output box
                Button(onClick = { ouexpanded = true }) {
                    Text(outputunit)
                    Icon(Icons.Default.ArrowDropDown,contentDescription = "select")
                }
                DropdownMenu(expanded = ouexpanded, onDismissRequest = { ouexpanded = false }) {
                    DropdownMenuItem(text = { Text("Centimeter")}, onClick = {
                        outputunit = "Centimeter"
                        ouexpanded = false
                        ouconversionfactor = 0.01
                        outputunit = "Centimeter"
                        convertunit()
                    })
                    DropdownMenuItem(text = { Text("meter")}, onClick = {
                        outputunit = "meter"
                        ouexpanded = false
                        ouconversionfactor = 1.00
                        outputunit = "meter"
                        convertunit()
                    })
                    DropdownMenuItem(text = { Text("feet")}, onClick = {
                        outputunit = "feet"
                        ouexpanded = false
                        ouconversionfactor = 0.3048
                        outputunit = "feet"
                        convertunit()
                    })
                    DropdownMenuItem(text = { Text("millimeter")}, onClick = {
                        outputunit = "millimeter"
                        ouexpanded = false
                        ouconversionfactor = 0.001
                        outputunit = "millimeter"
                        convertunit()
                    })
                }
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text("Result: $outputvalue $outputunit", style = MaterialTheme.typography.headlineMedium)
    }


}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    UnitconverterTheme {
      unitConverter()
    }
}