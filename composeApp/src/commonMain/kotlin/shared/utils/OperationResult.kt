package shared.utils

sealed class OperationResult {
    object Created : OperationResult()
    object Updated : OperationResult()
    object Failed : OperationResult()
}