package com.example.medreminder.ui.component.detail


import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.medreminder.R
import com.example.medreminder.data.remote.drugapi.model.DisplayDrug
import com.example.medreminder.ui.component.InfoTextItem
import com.example.medreminder.ui.theme.adverseReactionsCardColor
import com.example.medreminder.ui.theme.adverseReactionsTitleColor
import com.example.medreminder.ui.theme.brandCardColor
import com.example.medreminder.ui.theme.brandTitleColor
import com.example.medreminder.ui.theme.cardBorderColor
import com.example.medreminder.ui.theme.detailTextColor
import com.example.medreminder.ui.theme.dosageCardColor
import com.example.medreminder.ui.theme.dosageTitleColor
import com.example.medreminder.ui.theme.genericCardColor
import com.example.medreminder.ui.theme.genericTitleColor
import com.example.medreminder.ui.theme.indicationsCardColor
import com.example.medreminder.ui.theme.indicationsTitleColor
import com.example.medreminder.ui.theme.manufacturerCardColor
import com.example.medreminder.ui.theme.manufacturerTitleColor
import com.example.medreminder.ui.theme.warningsCardColor
import com.example.medreminder.ui.theme.warningsTitleColor

@Composable
fun DrugDetailCard(drug: DisplayDrug?) {
    //Hauptspalte für die Medikamentendetails mit Scroll-Funktion
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        //Symbol für die Medikamenteninformationen
        Icon(
            painter = painterResource(R.drawable.medicine_information),
            contentDescription = "medicine_information_icon",
            modifier = Modifier
                .size(84.dp)
                .padding(8.dp),
            tint = Color.Unspecified
        )

        // Brand Name Card
        ElevatedCard(
            modifier = Modifier
                .fillMaxWidth()
                .size(width = 200.dp, height = 300.dp)
                .verticalScroll(rememberScrollState())
                .padding(vertical = 4.dp)
                .border(
                    1.dp,
                    shape = RoundedCornerShape(20.dp),
                    color = cardBorderColor
                ),
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.elevatedCardColors(
                containerColor = brandCardColor
            )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                InfoTextItem(
                    title = stringResource(R.string.label_brand_name),
                    color = brandTitleColor,
                    textAlign = TextAlign.Center
                )
                InfoTextItem(
                    title = if (drug?.brandName.isNullOrEmpty()) {
                        stringResource(id = R.string.unknown_drug)
                    } else {
                        drug.brandName
                    },
                    color = detailTextColor,
                    textAlign = TextAlign.Center
                )
            }
        }
        Spacer(modifier = Modifier.height(10.dp))

        // Generic Name Card
        ElevatedCard(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 4.dp)
                .size(width = 200.dp, height = 300.dp)
                .verticalScroll(rememberScrollState())
                .border(
                    1.dp,
                    shape = RoundedCornerShape(20.dp),
                    color = cardBorderColor
                ),
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.elevatedCardColors(
                containerColor = genericCardColor
            )
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                InfoTextItem(
                    title = stringResource(R.string.label_generic_name),
                    color = genericTitleColor,
                    textAlign = TextAlign.Center
                )
                InfoTextItem(
                    title = if (drug?.genericName.isNullOrEmpty()) {
                        stringResource(id = R.string.unknown_drug)
                    } else {
                        drug.genericName
                    },
                    color = detailTextColor,
                    textAlign = TextAlign.Center
                )
            }
        }
        Spacer(modifier = Modifier.height(10.dp))

        // Manufacturer Name Card
        ElevatedCard(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 4.dp)
                .size(width = 200.dp, height = 300.dp)
                .verticalScroll(rememberScrollState())
                .border(
                    1.dp,
                    shape = RoundedCornerShape(20.dp),
                    color = cardBorderColor
                ),
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.elevatedCardColors(
                containerColor = manufacturerCardColor
            )
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                InfoTextItem(
                    title = stringResource(R.string.label_manufacturer_name),
                    color = manufacturerTitleColor,
                    textAlign = TextAlign.Center
                )
                InfoTextItem(
                    title = if (drug?.manufacturerName.isNullOrEmpty()) {
                        stringResource(id = R.string.unknown_drug)
                    } else {
                        drug.manufacturerName
                    },
                    color = detailTextColor,
                    textAlign = TextAlign.Center
                )
            }
        }
        Spacer(modifier = Modifier.height(10.dp))

        // Indications Card
        ElevatedCard(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 4.dp)
                .size(width = 200.dp, height = 300.dp)
                .verticalScroll(rememberScrollState())
                .border(
                    1.dp,
                    shape = RoundedCornerShape(20.dp),
                    color = cardBorderColor
                ),
            shape = RoundedCornerShape(20.dp),

            colors = CardDefaults.elevatedCardColors(
                containerColor = indicationsCardColor
            )
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                InfoTextItem(
                    title = stringResource(R.string.label_indications_and_usage),
                    color = indicationsTitleColor,
                    textAlign = TextAlign.Center
                )
                InfoTextItem(
                    title = if (drug?.indicationsAndUsage.isNullOrEmpty()) {
                        stringResource(R.string.no_information_available)
                    } else {
                        drug.indicationsAndUsage
                    },
                    color = detailTextColor,
                    textAlign = TextAlign.Justify
                )
            }
        }
        Spacer(modifier = Modifier.height(10.dp))

        // Dosage Card
        ElevatedCard(
            modifier = Modifier
                .fillMaxWidth()
                .size(width = 200.dp, height = 300.dp)
                .verticalScroll(rememberScrollState())
                .padding(vertical = 4.dp)
                .border(
                    1.dp,
                    shape = RoundedCornerShape(20.dp),
                    color = cardBorderColor
                ),
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.elevatedCardColors(
                containerColor = dosageCardColor
            )
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                InfoTextItem(
                    title = stringResource(R.string.label_dosis),
                    color = dosageTitleColor,
                    textAlign = TextAlign.Center
                )
                InfoTextItem(
                    title = if (drug?.dosageAndAdministration.isNullOrEmpty()) {
                        stringResource(R.string.no_information_available)
                    } else {
                        drug.dosageAndAdministration
                    },
                    color = detailTextColor,
                    textAlign = TextAlign.Justify
                )
            }
        }

        Spacer(modifier = Modifier.height(10.dp))
        // Warnings Card
        ElevatedCard(
            modifier = Modifier
                .fillMaxWidth()
                .size(width = 200.dp, height = 300.dp)
                .verticalScroll(rememberScrollState())
                .padding(vertical = 4.dp)
                .border(
                    1.dp,
                    shape = RoundedCornerShape(20.dp),
                    color = cardBorderColor
                ),
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.elevatedCardColors(

                containerColor = warningsCardColor
            )
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                InfoTextItem(
                    title = stringResource(R.string.label_warnings),
                    color = warningsTitleColor,
                    textAlign = TextAlign.Center
                )
                InfoTextItem(
                    title = if (drug?.warnings.isNullOrEmpty()) {
                        stringResource(R.string.no_information_available)
                    } else {
                        drug.warnings
                    },
                    color = detailTextColor,
                    textAlign = TextAlign.Justify
                )
            }
        }

        Spacer(modifier = Modifier.height(10.dp))

        // Adverse Reactions Card
        ElevatedCard(
            modifier = Modifier
                .fillMaxSize()
                .size(width = 200.dp, height = 300.dp)
                .verticalScroll(rememberScrollState())
                .padding(vertical = 4.dp)
                .border(
                    1.dp,
                    shape = RoundedCornerShape(20.dp),
                    color = cardBorderColor
                ),
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.elevatedCardColors(
                containerColor = adverseReactionsCardColor
            )
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                InfoTextItem(
                    title = stringResource(R.string.label_adverse_reactions),
                    color = adverseReactionsTitleColor,
                    textAlign = TextAlign.Center
                )
                InfoTextItem(
                    title = if (drug?.adverseReactions.isNullOrEmpty()) {
                        stringResource(R.string.no_information_available)
                    } else {
                        drug.adverseReactions
                    },
                    color = detailTextColor,
                    textAlign = TextAlign.Justify
                )
            }
        }
    }
}