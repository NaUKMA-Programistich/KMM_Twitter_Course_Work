import SwiftUI
import shared
import Combine

struct LoginScreen: View {
    @State private var viewModel = LoginViewModel()

    @EnvironmentObject var twitterAPI: TwitterAPI
    @State private var isUserInProgress = false
    @State private var isUserDone = false

    var body: some View {
        WrapperScreen(
            viewModel: viewModel,
            content: { LoginView(state: $0, obtainEvent: $1) },
            onAction: { action in onAction(action: action) }
        )
        .navigationTitle("Login Screen")
        .sheet(isPresented: $isUserInProgress) {
            TwitterLoginView(url: $twitterAPI.authorizationURL)
        }
        .fullScreenCover(isPresented: $isUserDone) {
            MainScreen()
        }
        .onReceive(twitterAPI.$authState) {
            onTwitterAuthState(authState: $0)
        }
    }
    
    func onAction(action: LoginAction?) {
        print("Login action \(action)")
        switch(action){
        case LoginAction.OpenTwitterAuth():
            twitterAPI.authorize()
        case LoginAction.NavigateToAccount():
            isUserDone = true
        default: ()
        }
    }
    
    func onTwitterAuthState(authState: TwitterLoginResult) {
        switch (authState){
        case .loading: ()
            self.isUserInProgress = true
        case .none: ()
            self.isUserInProgress = false
        case .final(let authData):
            viewModel.obtainEvent(viewEvent: LoginEvent.LoginSuccess(data: authData))
            self.isUserInProgress = false
        case .error:
            self.isUserInProgress = false
        }
    }
}
