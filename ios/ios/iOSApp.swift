import SwiftUI
import shared

@main
struct iOSApp: App {
    @ObservedObject var twitterAPI = TwitterAPI()
    private let viewModel: SplashViewModel
    private let isExistAuth: Bool
    
    init() {
        print("App init")
        SharedDIKt.doInitDI()

        viewModel = SplashViewModel()
        isExistAuth = viewModel.isExistToken()
    }
    
	var body: some Scene {
		WindowGroup {
            NavigationView {
                if isExistAuth { MainScreen() }
                else { LoginScreen() }
            }
            .environmentObject(twitterAPI)
            .onOpenURL { url in
                print("open url \(url)")
                if isExistTwitterCallback(url: url) {
                    twitterAPI.onOAuthRedirect.send(url)
                }
            }
		}
	}
}
