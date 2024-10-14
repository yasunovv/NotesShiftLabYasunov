package com.yasunov.notesshiftlabyasunov

//@Composable
//fun ShiftApp(
//    modifier: Modifier = Modifier
//) {
//    val navController = rememberNavController()
//    ShiftScaffold(
//        bottomBar = {
//            BottomBar(
//                navController = navController,
//                modifier = modifier
//            )
//        }
//    ) { padding ->
//
//
//        Column(modifier.padding(padding)) {
//            NavHost(navController = navController, startDestination = CatalogDest) {
//                composable<CatalogDest> {
//                    CatalogScreen(
//                        navigateOnClick = { id ->
//                            navController.navigate(PizzaCardDest(id = id))
//                        }
//                    )
//                }
//                composable<PizzaCardDest> { backStackEntry ->
//                    val pizzaCardDest: PizzaCardDest = backStackEntry.toRoute()
//                    PizzaCardScreen(
//                        id = pizzaCardDest.id,
//                        onBackIconClicked = { navController.popBackStack() },
//                        onButtonNextClicked = {
//                            navController.navigate(CartDest)
//                        }
//                    )
//                }
//                composable<CartDest> {
//                    CartScreen(onBackIconClicked = { navController.popBackStack() })
//                }
//                composable<OrdersDest> {
//                    OrdersScreen()
//                }
//                composable<ProfileDest> {
//                    ProfileScreen()
//                }
//
//
//
//            }
//        }
//
//    }
//
//
//}
