import Foundation

func isExistTwitterCallback(url: URL) -> Bool {
    guard let urlScheme = url.scheme,
          let callbackURL = URL(string: "\(TwitterAPI.ClientCredentials.CallbackURLScheme)://"),
          let callbackURLScheme = callbackURL.scheme
    else { return false }

    guard urlScheme.caseInsensitiveCompare(callbackURLScheme) == .orderedSame else { return false }
    
    return true
}
