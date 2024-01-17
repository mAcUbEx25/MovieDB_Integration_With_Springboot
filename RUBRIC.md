# MonetaGo Code Challenge Rubric

|                           | Senior                                                                                                                                                      | Mid                                                                                                                                                                | Junior                                                                                                                                                                             | Beginner                                                                                                                                                                           | 
|---------------------------|-------------------------------------------------------------------------------------------------------------------------------------------------------------|--------------------------------------------------------------------------------------------------------------------------------------------------------------------|------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| Specification Fulfillment | 9                                                                                                                                                           | 6                                                                                                                                                                  | 3                                                                                                                                                                                  | 0                                                                                                                                                                                  |
|                           | All details of the spec are implemented. Incorrect behavior is non-existent or only exists in rare edge cases and does not affect application usability.    | Most details of the spec are implemented correctly. Incorrect behavior is seldom exhibited and has minimal impact on application usability.                        | Most details of the spec are implemented correctly. Incorrect behavior is seldom exhibited and has noticeable impact on application usability                                      | Significant details of spec are not implemented or are implemented incorrectly. Incorrect behavior is often exhibited.                                                             |
| Maintainability           | 6                                                                                                                                                           | 4                                                                                                                                                                  | 2                                                                                                                                                                                  | 0                                                                                                                                                                                  |
|                           | Class or functions only intake required inputs and give reliable outputs. Concerns are separated and functions have only intended or required side effects. | Class or functions only intake required inputs and give reliable outputs. Concerns are separated but functions have unintended or more than required side effects. | Classes or functions rely on too many inputs, but give reliable outputs. Attempts are made to separate concerns, but functions have unintended or more than required side effects. | Classes or functions rely on too many inputs and give unreliable outputs. No attempts made to separate concerns, functions have unintended or more side effects than required.     |
| Extensibility             | 6                                                                                                                                                           | 4                                                                                                                                                                  | 2                                                                                                                                                                                  | 0                                                                                                                                                                                  |
|                           | Code is as DRY as possible. Classes or functions can be reused without any refactoring.                                                                     | Most code is DRY, with only a few areas of improvement. Classes or functions can be reused, but may require some refactoring.                                      | An attempt is made at keeping code DRY, but could be greatly improved. Classes or functions cannot be reused without some refactoring.                                             | No attempt made at keeping code DRY. Classes or functions cannot be reused without major refactoring.                                                                              |
| Error Handling            | 3                                                                                                                                                           | 2                                                                                                                                                                  | 1                                                                                                                                                                                  | 0                                                                                                                                                                                  |
|                           | Error handling exists and is handled consistently through the codebase. Edge cases are rare or non-existent.                                                | Error handling exists but is not always handled consistently through the codebase. Some edge cases are not handled properly.                                       | Error handling exists but is inconsistent. Some errors crash the program.                                                                                                          | No attempt at error handling. Errors frequently crash the program.                                                                                                                 |
| Project Structure         | 3                                                                                                                                                           | 2                                                                                                                                                                  | 1                                                                                                                                                                                  | 0                                                                                                                                                                                  |
|                           | Organization is intuitive with logical separation of classes or functions based on purpose.                                                                 | Organization is intuitive to follow. Some classes or functions live side by side when they should be in separate files based on  purpose                           | Organization is attempted but is not intuitive. Many classes or functions live side by side when they should be in separate files based on purpose.                                | No attempt at logical organization is made. All files are in the root of the project or placed in pre-existing subfolders. No separation of classes or functions based on purpose. |
| Documentation             | 3                                                                                                                                                           | 2                                                                                                                                                                  | 1                                                                                                                                                                                  | 0                                                                                                                                                                                  |
|                           | All classes or functions are self documenting. Those that are not have clarifying comments.                                                                 | Most classes or functions are self documenting. Those that are not have clarifying comments.                                                                       | Some classes or functions are self documenting. Those that are not do  not have clarifying comments.                                                                               | Little to no classes or functions are self documenting. There are no clarifying comments.                                                                                          |