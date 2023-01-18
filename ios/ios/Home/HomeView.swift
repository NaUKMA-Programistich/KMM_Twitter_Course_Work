import SwiftUI
import shared

struct HomeView : View {
    let state: HomeState
    let obtainEvent: (KotlinUnit) -> Void

    var body: some View {
        switch (state) {
        case let state as HomeState.Success:
            ScrollView {
                VStack(alignment: .leading) {
                    ForEach(state.tweets, id: \.id) { tweet in
                        TweetView(tweet: tweet)
                    }
                }
            }
            .padding(.top, 1)
        case let state as HomeState.Error:
            Text(state.error.message ?? "Error")
        case HomeState.Loading():
            ProgressView()
        default:
            EmptyView()
        }
    }
}

struct TweetView : View {
    let tweet: Tweet


    var body: some View {
        VStack(alignment: .leading) {
            Text(tweet.text ?? "")
            Divider()
        }
    }
}
