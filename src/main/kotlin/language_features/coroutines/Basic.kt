package language_features.coroutines

import kotlin.coroutines.Continuation

class Basic {
    /**
     * system move suspension function to thread pool
     * waits for further command
     * program skip over to rest of the function code
     * until it reach first line that need to use result of that function
     * it `await` the result, the suspension function start to be executed(or was executed when we put in coroutine?), if it isnt ready, block the program
     *
     * coroutines is not thread, it utilized thread pools
     * so you can create millions of coroutines, without overflowing the memory
     * Whenever a coroutine is suspended, the current stack frame (the place that Kotlin uses to keep track of which function is running and its variables)
     * is copied and saved for later. When it resumes, the stack frame is copied back from where it was saved and starts running again.
     *
     */
    val con : Continuation
}