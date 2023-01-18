import SwiftUI
import shared
import Combine

struct HomeScreen: View {
    @State private var viewModel = HomeViewModel()

    var body: some View {
        WrapperScreen(
            viewModel: viewModel,
            content: { HomeView(state: $0, obtainEvent: $1) },
            onAction: { action in () }
        )
    }
}

struct HomeScreen_Previews: PreviewProvider {
    static var previews: some View {
        HomeScreen()
    }
}
