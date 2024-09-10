package com.example.appEntregable.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Preview(showSystemUi = true)
@Composable
fun ScaffoldPantallaNavegacion() {
    PantallaNavegacion()
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun PantallaNavegacion() {
    val navController = rememberNavController()
    var tamanoFuente by remember { mutableStateOf(12) }
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Países Latinoamericanos")
                },
                navigationIcon = {
                    IconButton(onClick = {  }) {
                        Icon(Icons.Filled.Menu, contentDescription = null)
                    }
                },
                actions = {
                    IconButton(onClick = { tamanoFuente++ }) {
                        Icon(Icons.Default.Add, contentDescription = null)
                    }
                    IconButton(onClick = { tamanoFuente=12 }) {
                        Icon(Icons.Filled.Home, contentDescription = null)
                    }
                }
            )
        },
        bottomBar = {
            BottomNavigation() {
                var argentina by remember { mutableStateOf(true) }
                var colombia by remember { mutableStateOf(false) }
                var brasil by remember { mutableStateOf(false) }
                BottomNavigationItem(selected = argentina,
                    onClick = {
                        argentina = true;colombia = false;brasil = false;
                        navController.navigate("pantallaargentina")
                    },
                    icon = { Icon(Icons.Default.Info, contentDescription = null) },
                    label = {
                        Text(text = "Argentina")
                    }
                )
                BottomNavigationItem(selected = brasil,
                    onClick = {
                        brasil = true;argentina = false;colombia = false;
                        navController.navigate("pantallabrasil")
                    },
                    icon = { Icon(Icons.Default.Info, contentDescription = null) },
                    label = {
                        Text(text = "Brasil")
                    }
                )
                BottomNavigationItem(selected = colombia,
                    onClick = {
                        colombia = true;argentina = false;brasil = false;
                        navController.navigate("pantallacolombia")
                    },
                    icon = { Icon(Icons.Default.Info, contentDescription = null) },
                    label = {
                        Text(text = "Colombia")
                    }
                )
            }
        }
    ) {
        NavHost(navController = navController, startDestination = "pantallaargentina") {
            composable("pantallaargentina") {
                PantallaArgentina(tamanoFuente)
            }
            composable("pantallabrasil") {
                PantallaBrasil(tamanoFuente)
            }
            composable("pantallacolombia") {
                PantallaColombia(tamanoFuente)
            }
        }
    }
}

@Composable
fun PantallaArgentina(tamanoFuente:Int) {
    val scroll = rememberScrollState(0)
    Text(
        fontSize=tamanoFuente.sp,
        text = "Argentina, oficialmente República Argentina,e\u200B es un país soberano de América del Sur, ubicado en el extremo sur y sudeste de dicho subcontinente. Adopta la forma de gobierno republicana, democrática, representativa y federal.\n" +
                "\n" +
                "La Argentina está organizada como un Estado federal descentralizado, integrado desde 1994 por un Estado nacional y 24 jurisdicciones de primer orden9\u200B o estados autogobernados,10\u200B11\u200B que son 23 provincias y la Ciudad Autónoma de Buenos Aires (CABA), esta última designada como Capital Federal del país. Todos los estados autogobernados tienen constitución, bandera y fuerza de seguridad propios. Las 23 provincias mantienen todos los poderes no delegados al Estado nacional, tienen tres poderes autónomos y garantizan la autonomía de sus municipios.12\u200B13\u200B\n" +
                "\n" +
                "Integra el Mercosur —bloque del que fue fundador en 1991—, la Comunidad de Estados Latinoamericanos y Caribeños (CELAC) y la Organización de Estados Americanos (OEA).\n" +
                "\n" +
                "En 2019, su Índice de Desarrollo Humano (IDH) fue del 0,845, ubicándolo en el grupo de países de desarrollo humano muy alto, en el puesto 46.14\u200B Ajustado por desigualdad la Argentina retrocede cuatro lugares en la clasificación, en tanto que según el índice de desigualdad de género su ubicación retrocede al puesto 75.14\u200B En educación la ley establece que el gasto público en educación no debe ser inferior al 6 % del PBI, aunque pese a ello el gasto se ubicó en 5,5 % del PBI en 201715\u200B con una tasa de alfabetismo de las personas mayores de 15 años superior al 99 %.16\u200B\n" +
                "\n" +
                "La economía argentina es la segunda más desarrollada e importante en Sudamérica. Según el Banco Mundial, su PIB nominal es el 27º del mundo.17\u200B Debido a su importancia geopolítica y económica, es uno de los tres estados soberanos latinoamericanos que forma parte del denominado Grupo de los 20 e integra además el grupo de los NIC o nuevos países industrializados.18\u200B\n" +
                "\n" +
                "Es el único país latinoamericano que tiene un centro de investigación y enseñanza científica entre los diez mejores del mundo,19\u200B y el país iberoamericano con mayor cantidad de premios Nobel en ciencias. Su capacidad tecnológica y científica le ha permitido diseñar, producir y exportar satélites,20\u200B construir reactores nucleares y ser el primer productor de software, aeronaves, entre otras cosas. Es considerada una potencia regional.21\u200B\n" +
                "\n" +
                "Ha brindado una creciente cooperación nuclear a países de América Latina, el Magreb, el Golfo Pérsico, el Sudeste Asiático y Oceanía, a partir de las capacidades desarrolladas por la Comisión Nacional de Energía Atómica (CNEA) y por la prestigiosa empresa estatal INVAP.22\u200B Es el país latinoamericano que más premios Nobel ha ganado —cinco en total—, tres de ellos vinculados con la ciencia.\n" +
                "\n" +
                "Es un país bicontinental con una superficie de 2 780 400 km²,2\u200B es el país hispanohablante más extenso del planeta, el segundo más grande de América Latina y octavo en el mundo, si se considera solo la superficie continental sujeta a soberanía efectiva. Su plataforma continental, reconocida por la ONU en 2016, alcanza los 6 581 500 km²,23\u200B convirtiéndose en una de las más grandes del mundo,24\u200B extendiéndose desde el continente americano hasta el Polo Sur en la Antártida, a través de Atlántico Sur. Si se cuentan las islas Malvinas, Georgias del Sur, Sandwich del Sur y otras numerosas islas menores (administradas por el Reino Unido, pero de soberanía en litigio), más una porción del área antártica llamada Antártida Argentina al sur del paralelo 60° S, sobre la cual Argentina reclama soberanía, la superficie se eleva a 3 761 274 km².25\u200B Es uno de los veinte países que tienen presencia permanente en la Antártida, siendo entre ellos el que tiene mayor cantidad de bases permanentes, con seis bases en total.\n" +
                "\n" +
                "Su territorio reúne una gran diversidad de climas, causada por una amplitud latitudinal que supera los 30° —incluyendo varias zonas geoastronómicas—, una diferencia en la altitud que va de 107 m bajo el nivel del mar (Laguna del Carbón) a casi 7000 msnm y la extensión del litoral marítimo que alcanza 4725 km. Amplias llanuras húmedas limitan con extensos desiertos y altas montañas, mientras que la presencia de climas tropicales y subtropicales en el norte, contrastan con las nevadas y fríos extremos en las zonas cordilleranas y el sur.\n" +
                "\n" +
                "Su territorio continental americano, que abarca gran parte del Cono Sur, limita al norte con Bolivia y Paraguay, al nordeste con Brasil, al este con Uruguay y el océano Atlántico, al oeste con Chile y, siempre en su sector americano, al sur con Chile y las aguas atlánticas del pasaje de Drake.\n" +
                "\n" +
                "Los primeros registros de pobladores en el actual territorio argentino se remontan a los trece mil años AP, durante el Paleoamericano. En tiempos protohistóricos, periodo precolombino, fue habitado por numerosos pueblos indígenas, algunos de los cuales aún habitan el país; entre ellos guaycurúes, guaraníes, mapuches, tehuelches y diaguitas, estos últimos formaban parte del Imperio Incaico. La colonización española del actual territorio argentino comenzó con viajes exploratorios desde el año 1512, el establecimiento de una población en 1528 y la distribución del territorio a los adelantados. Más tarde, quedó bajo la jurisdicción del Virreinato del Perú. En 1776, la Corona española fundó el Virreinato del Río de la Plata, el cual sería una entidad política precedente a la actual República Argentina. El 25 de mayo de 1810 alcanzó la independencia de facto cuando fue depuesto el último virrey español que gobernó desde Buenos Aires,26\u200B organizándose la Primera Junta de gobierno. El 9 de julio de 1816 fue proclamada la independencia en San Miguel de Tucumán",
        modifier = Modifier.verticalScroll(scroll)
    )
}

@Composable
fun PantallaBrasil(tamanoFuente:Int) {
    val scroll = rememberScrollState(0)
    Text(
        fontSize=tamanoFuente.sp,
        text = "Brasil, oficialmente República Federativa de Brasil8\u200B9\u200B10\u200B11\u200B (en portugués: República Federativa do Brasil, pron. AFI [ʁe'publikɐ fedeɾaˈt͡ʃivɐ 'dʊ braˈziw] ( escuchar)), es un país soberano de América del Sur que comprende la mitad oriental del continente y algunos grupos de pequeñas islas en el océano Atlántico. Es el país más grande de América Latina. Con una superficie estimada en más de 8,5 millones de km²,3\u200B es el quinto país más grande del mundo en área total (equivalente a 47 % del territorio sudamericano).12\u200B Delimitado por el océano Atlántico al este, Brasil tiene una línea costera de 7491 km.3\u200B Al norte limita con el departamento ultramarino francés de la Guayana Francesa, Surinam, Guyana y Venezuela; al noroeste con Colombia; al oeste con Perú y Bolivia; al suroeste con Paraguay y Argentina, y al sur con Uruguay. De este modo tiene frontera con todos los países de América del Sur, excepto Ecuador y Chile.13\u200B La mayor parte del país está comprendido entre los trópicos terrestres, por lo que las estaciones climáticas no se sienten de una manera radical en gran parte de su territorio. La selva amazónica cubre 3,6 millones de km² del territorio. Gracias a su vegetación y al clima, es uno de los países con más especies de animales en el mundo.14\u200B\n" +
                "\n" +
                "Brasil, hasta entonces habitado por indígenas, tuvo su primer contacto con los europeos en el año 1500 d.C. a través de una expedición portuguesa liderada por Pedro Álvares Cabral. Tras el Tratado de Tordesillas, el territorio brasileño fue el segmento del continente americano que correspondió al reino de Portugal, del cual obtuvo su independencia el 7 de septiembre de 1822. Así, el país pasó de ser parte central del reino de Portugal a un imperio para finalmente convertirse en una república. Su primera capital fue Salvador de Bahía, que fue sustituida por Río de Janeiro hasta que se construyó una nueva capital, Brasilia. Su constitución actual, formulada en 1988, define a Brasil como una república federativa presidencialista.11\u200B La federación está formada por la unión del Distrito Federal, los 26 estados y los 5570 municipios.11\u200B15\u200Bnota 1\u200B\n" +
                "\n" +
                "A pesar de que sus más de 210 millones de habitantes17\u200B hacen de Brasil el quinto país más poblado del mundo, presenta un bajo índice de densidad poblacional. Esto se debe a que la mayor parte de la población se concentra a lo largo del litoral, mientras que el interior del territorio aún está marcado por enormes vacíos demográficos. El idioma oficial y el más hablado es el portugués, que lo convierte en el mayor país lusófono del mundo.13\u200B Por su parte, la religión con más seguidores es el catolicismo, siendo el país con mayor número de católicos nominales del mundo. La sociedad brasileña es considerada una sociedad multiétnica al estar formada por descendientes de europeos, indígenas, africanos y asiáticos.\n" +
                "\n" +
                "La economía brasileña es la mayor de América Latina y del hemisferio sur, la sexta mayor del mundo por PIB nominal y la séptima mayor por paridad del poder adquisitivo (PPC).18\u200B19\u200B Es considerado una economía de renta media-alta por el Banco Mundial y un país recientemente industrializado, que tiene la mayor proporción de riqueza global de América Latina debido al abundante petróleo y diversos minerales encontrados fácilmente en el país.20\u200B21\u200B22\u200B Sin embargo, su abundante riqueza está muy concentrada, convirtiendo al país en uno de los más desiguales del mundo,23\u200B el 10% más rico de su población recibió el 54% de la renta nacional en 2018.24\u200B Como potencia regional y media, la nación tiene reconocimiento e influencia internacional, siendo que también es clasificada como una potencia global emergente y como una potencial superpotencia por varios analistas.25\u200B26\u200B\n" +
                "\n" +
                "El país es miembro fundador de la Organización de las Naciones Unidas (ONU), G20, Comunidad de Países de Lengua Portuguesa (CPLP), Unión Latina, Organización de los Estados Americanos (OEA), Organización de los Estados iberoamericanos (OEI), Mercado Común del Sur (Mercosur) y de la Unión de Naciones Sudamericanas (Unasur), además de ser uno de los países BRIC.",
        modifier = Modifier.verticalScroll(scroll)
    )
}

@Composable
fun PantallaColombia(tamanoFuente:Int) {
    val scroll = rememberScrollState(0)
    Text(
        fontSize=tamanoFuente.sp,
        text = "Colombia, oficialmente República de Colombia, es un país soberano situado en la región noroccidental de América del Sur, que se constituye en un estado unitario, social y democrático de derecho cuya forma de gobierno es presidencialista. Es una república organizada políticamente en 32 departamentos descentralizados y el Distrito Capital de Bogotá, sede del Gobierno Nacional.12\u200B\n" +
                "\n" +
                "Incluyendo la isla de Malpelo, el cayo Roncador y el banco Serrana, el país abarca una superficie de 1 141 748 km²,3\u200B por lo que es el vigesimoquinto país más grande del mundo y el séptimo más grande de América. Reclama como mar territorial el área hasta las 12 millas náuticas de distancia,4\u200B manteniendo un diferendo limítrofe al respecto con Venezuela y Nicaragua.13\u200B14\u200B Limita al oriente con Venezuela y Brasil, al sur con Perú y Ecuador y al occidente con Panamá; en cuanto a límites marítimos, colinda con Panamá, Costa Rica, Nicaragua, Honduras, Jamaica, Haití, República Dominicana y Venezuela en el mar Caribe, y con Panamá, Costa Rica y Ecuador en el océano Pacífico.15\u200B\n" +
                "\n" +
                "Es la única nación de América del Sur que tiene costas en el océano Pacífico y acceso al Atlántico a través del mar Caribe,16\u200B en los que posee diversas islas como el archipiélago de San Andrés, Providencia y Santa Catalina17\u200B\n" +
                "\n" +
                "Es el vigesimoctavo país más poblado del mundo, con una población de 51 millones de habitantes,18\u200B19\u200B además es la segunda nación con más hispanohablantes, solo detrás de México.20\u200B Posee una población multicultural, la cual refleja la influencia de la colonización europea a gran escala, pueblos nativos y mano de obra africana, con oleadas migratorias provenientes de Europa y Oriente Próximo durante el siglo XX.21\u200B El producto interno bruto de paridad de poder adquisitivo de Colombia ocupa el cuarto puesto en América Latina y el puesto 28 a nivel mundial. El PIB nominal colombiano es el cuarto más grande de América Latina y ocupa el puesto 28 a nivel mundial.22\u200B\n" +
                "\n" +
                "La presencia humana en Colombia se remonta a más de 14 500 años.23\u200B24\u200B 25\u200BDespués de miles de años de formación cultural, en el actual territorio colombiano surgieron diversas culturas precolombinas como los muiscas, taironas y quimbayas. Al colonizar a estas culturas, España creó el Virreinato de la Nueva Granada con capital en Santafé (hoy Bogotá). En el año 1810 comenzó la Guerra de independencia, tras la cual surgió el país que actualmente se conoce como Colombia. Durante los siglos XIX y XX, el país se caracterizó por su inestabilidad y un gran número de guerras civiles;26\u200B el último de estos conflictos, conocido como conflicto armado interno, comenzó en 1960. En el año 2012, después de más cincuenta años de conflicto, el gobierno del entonces presidente Juan Manuel Santos inició conversaciones de paz con las FARC-EP. En 2016 se alcanzó un acuerdo final que a pesar de no ser aprobado en el plebiscito del 2 de octubre del mismo año, fue implementado con modificaciones en 2017. A la fecha, el Gobierno de Colombia se encuentra adelantando el proceso de implementación de los acuerdos e iniciando nuevas conversaciones con el ELN, que ha manifestado la intención de contribuir al final del conflicto.\n" +
                "\n" +
                "Colombia tiene una economía diversificada y posee un importante componente de servicios. La producción económica del país está dominada por su demanda interna y el gasto en consumo de los hogares es el mayor componente del PIB.27\u200B El PIB en 2016 fue de 720 151 millones de dólares.28\u200B El índice de desarrollo humano colombiano es de 0.747 y su esperanza de vida promedio es de 75.1 años.7\u200B29\u200B Colombia es parte del grupo de los CIVETS, considerados como seis principales mercados emergentes. Es miembro de la OCDE,30\u200B la ONU, la OEA, la Alianza del Pacífico y de otras organizaciones internacionales; también es el único país de Latinoamérica que es socio global de la OTAN. Es el segundo país con mayor índice de desigualdad en América Latina, después de Brasil, y empatado con Panamá, según la base de datos del Banco Mundial.31\u200B\n" +
                "\n" +
                "Es la segunda nación más biodiversa del mundo, contando con 54 871 especies registradas;32\u200B no obstante, un estudio lo ubica entre los ocho países responsables de la mitad de la destrucción de biodiversidad en el mundo.33\u200B Por otra parte, es el país de América Latina con más conflictos ecológicos entre la población local y empresas multinacionales en áreas de especial protección ambiental.34\u200B35\u200B Para proteger su medio ambiente el país cuenta con instrumentos como la Política Nacional de Cambio Climático y el impuesto al carbono.36\u200BLa producción de electricidad en Colombia proviene principalmente de fuentes de energía renovables. 69.97 % se obtiene de la generación hidroeléctrica.37\u200B",
        modifier = Modifier.verticalScroll(scroll)
    )
}
