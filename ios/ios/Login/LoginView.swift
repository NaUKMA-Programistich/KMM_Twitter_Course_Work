import SwiftUI
import shared

struct LoginView : View {
    let state: LoginState
    let obtainEvent: (LoginEvent) -> Void
    
    var body: some View {
        let _ = print("Login state \(state)")
        switch (state) {
        case LoginState.EntryDisplay():
            Button("Login via Twitter") {
                obtainEvent(LoginEvent.ClickLogin())
            }
        case LoginState.Loading():
            ProgressView()
        default:
            EmptyView()
        }
    }
}
          
