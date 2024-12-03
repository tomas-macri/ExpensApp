import SwiftUI
import ComposeApp

@main
struct iOSApp: App {
    @Environment(\.scenePhase) private var scenePhase
    @State private var isDarkMode: Bool = UITraitCollection.current.userInterfaceStyle == .dark
    
    init() {
        MainViewControllerKt.doInitKoin()
    }
    var body: some Scene {
        WindowGroup {
            ContentView(isDarkMode: $isDarkMode)
                .onChange(of: scenePhase) { newScenePhase in
                    if newScenePhase == .active {
                        let osTheme = UIScreen.main.traitCollection.userInterfaceStyle
                        isDarkMode = osTheme == .dark
                        
                    }
                }.preferredColorScheme(isDarkMode ? .dark : .light)
        }
    }
}
