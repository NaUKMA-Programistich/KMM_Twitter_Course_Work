import SwiftUI
import SafariServices

struct TwitterLoginView: UIViewControllerRepresentable {
    class SafariViewControllerWrapper: UIViewController {
        private var safariViewController: SFSafariViewController?
        
        var url: URL? {
            didSet {
                if let safariViewController = safariViewController {
                    safariViewController.willMove(toParent: self)
                    safariViewController.view.removeFromSuperview()
                    safariViewController.removeFromParent()
                    self.safariViewController = nil
                }
                
                guard let url = url else { return }
                
                let newSafariViewController = SFSafariViewController(url: url)
                addChild(newSafariViewController)
                newSafariViewController.view.frame = view.frame
                view.addSubview(newSafariViewController.view)
                newSafariViewController.didMove(toParent: self)
                self.safariViewController = newSafariViewController
            }
        }
        
        override func viewDidLoad() {
            super.viewDidLoad()
            self.url = nil
        }
    }
    
    typealias UIViewControllerType = SafariViewControllerWrapper

    @Binding var url: URL?

    func makeUIViewController(context: UIViewControllerRepresentableContext<TwitterLoginView>) -> SafariViewControllerWrapper {
        return SafariViewControllerWrapper()
    }

    func updateUIViewController(_ safariViewControllerWrapper: SafariViewControllerWrapper, context: UIViewControllerRepresentableContext<TwitterLoginView>) {
        if safariViewControllerWrapper.url != url {
            safariViewControllerWrapper.url = url
        }
    }
}
