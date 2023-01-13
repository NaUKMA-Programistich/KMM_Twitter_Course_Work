import shared
import Combine
import SwiftUI

func toPublisher<T>(_ flow: KMMFlow<T>) -> AnyPublisher<T, Never> {
    return Deferred<Publishers.HandleEvents<PassthroughSubject<T, Never>>> {
        let subject = PassthroughSubject<T, Never>()
        let cancelable = flow.observe { next in
            if let next = next {
                subject.send(next)
            }
        }
        return subject.handleEvents(receiveCancel: {
            cancelable.cancel()
        })
    }.eraseToAnyPublisher()
}

