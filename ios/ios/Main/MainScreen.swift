import SwiftUI

struct MainScreen: View {
    var body: some View {
        TabView {
            HomeScreen()
                .tabItem {
                    Label("Home", systemImage: "house")
                }
            AccountScreen()
                .tabItem {
                    Label("Account", systemImage: "person")
                }
            }
        }
}
