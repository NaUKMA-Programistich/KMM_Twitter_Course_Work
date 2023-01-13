import shared
import Combine
import SwiftUI


private class ObservableModel<StateObserved>: ObservableObject {
    @Published var stateObserved: StateObserved?
    
    init(statePublisher: AnyPublisher<StateObserved, Never>) {
        statePublisher
            .compactMap { $0 }
            .receive(on: DispatchQueue.main)
            .assign(to: &$stateObserved)
    }
}


public struct ObservingView<StateObserved, UI>: View where UI: View {
    
    @ObservedObject private var model: ObservableModel<StateObserved>
    private let content: (StateObserved) -> UI
    @State private var viewState: StateObserved? = nil

    init(statePublisher: AnyPublisher<StateObserved, Never>, @ViewBuilder content: @escaping (StateObserved) -> UI) {
        model = ObservableModel(statePublisher: statePublisher)
        self.content = content
    }
    
    public var body: some View {
        if let viewState = model.stateObserved {
            return AnyView(content(viewState))
        } else {
            return AnyView(EmptyView())
        }
    }
}
