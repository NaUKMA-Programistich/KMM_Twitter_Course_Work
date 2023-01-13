import shared
import Combine
import SwiftUI

struct WrapperScreen<VM, ViewState, Action, Event, UI> : View where VM: ViewModel<ViewState, Action, Event>, UI: View {
    
    private let viewModel: VM
    private let content: (ViewState, @escaping (Event) -> Void) -> UI
    private let onAction: (Action?) -> Void

    init(
        viewModel: VM,
        content: @escaping (ViewState, @escaping (Event) -> Void) -> UI,
        onAction: @escaping (Action?) -> Void)
    {
        self.viewModel = viewModel
        self.content = content
        self.onAction = onAction
    }
    
    
    var body: some View {
        ObservingView(statePublisher: toPublisher(viewModel.viewStates())) { state in
            content(state) { event in
                viewModel.obtainEvent(viewEvent: event)
            }
        }.onReceive(toPublisher(viewModel.viewActions())) { action in
            onAction(action)
        }
    }
}
