package shared.utils

sealed class OperationResult {
    data object Created : OperationResult()
    data object Updated : OperationResult()
    data object Failed : OperationResult()
}