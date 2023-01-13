import SwiftUI

struct MainScreen: View {
    var body: some View {
        TabView {
            Text("Home Screen")
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