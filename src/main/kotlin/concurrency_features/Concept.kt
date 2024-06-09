package concurrency_features

//https://kotlinlang.org/docs/shared-mutable-state-and-concurrency.html#thread-confinement-fine-grained
// in order to do thread safe

//There is a common misconception that making a variable volatile solves concurrency problem.
//because volatile variables guarantee linearizable (this is a technical term for "atomic") reads and writes to the corresponding variable, but do not provide atomicity of larger actions (increment in our case).

// use Thread-safe data structures
//a thread-safe (aka synchronized, linearizable, or atomic) data structure
// that provides all the necessary synchronization for the corresponding operations
// that needs to be performed on a shared state.
// e.g. we can use AtomicInteger class which has atomic incrementAndGet operations

//Thread confinement fine-grained
//Thread confinement is an approach to the problem of shared mutable state where all access to the particular
// shared state is confined to a single thread. It is typically used in UI applications, where all UI state
// is confined to the single event-dispatch/application thread

//Mutual exclusion
//Mutual exclusion solution to the problem is to protect all modifications of the shared state with a critical section that is never executed concurrently