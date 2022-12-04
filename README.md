# advent-of-code-2022

Welcome to the Advent of Code[^aoc] Kotlin project created by [tbilou][github] using the [Advent of Code Kotlin Template][template] delivered by JetBrains.

In this repository, tbilou is about to provide solutions for the puzzles using Kotlin language.

Day | success?       | Notes
----|-------------|-------------------------------------------------
01  | [✓][day01]  | [Elves calorie counting][aoc01]. I reached the solution using a simple for loop. Later on I introduced an extension function to split a list into a list of lists.
02  | [✓][day02]  | [Rock paper scissors scoring][aoc02]. Tried to use enums and data classes but gave up and ended up hard-coding 2 truth tables 
03  | [✓][day03]  | [Rucksack Reorganization][aoc03]. The `intersect` function saved the day ;) 
04  | [✓][day04]  | [Camp Cleanup][aoc04]. The plan was to subtract the smallest "range" from the larger one and check how many items were left. If there was nothing left, then it was fully contained. For the second part I joined both lists and compared the size with a set of the same lists. 
05  |             |
06  |             |
07  |             |
08  |             |
09  |             |
10  |             |
11  |             |
12  |             |
13  |             |
14  |             |
15  |             |
16  |             |
17  |             |
18  |             |
19  |             |
20  |             |
21  |             |
22  |             |
23  |             |
24  |             |
25  |             |

[aoc01]: http://adventofcode.com/2020/day/1
[aoc02]: http://adventofcode.com/2020/day/2
[aoc03]: http://adventofcode.com/2020/day/3
[aoc04]: http://adventofcode.com/2020/day/4
[aoc05]: http://adventofcode.com/2020/day/5
[aoc06]: http://adventofcode.com/2020/day/6
[aoc07]: http://adventofcode.com/2020/day/7
[aoc08]: http://adventofcode.com/2020/day/8
[aoc09]: http://adventofcode.com/2020/day/9
[aoc10]: http://adventofcode.com/2020/day/10
[aoc11]: http://adventofcode.com/2020/day/11
[aoc12]: http://adventofcode.com/2020/day/12
[aoc13]: http://adventofcode.com/2020/day/13
[aoc14]: http://adventofcode.com/2020/day/14
[aoc15]: http://adventofcode.com/2020/day/15
[aoc16]: http://adventofcode.com/2020/day/16
[aoc17]: http://adventofcode.com/2020/day/17
[aoc18]: http://adventofcode.com/2020/day/18
[aoc19]: http://adventofcode.com/2020/day/19
[aoc20]: http://adventofcode.com/2020/day/20
[aoc21]: http://adventofcode.com/2020/day/21
[aoc22]: http://adventofcode.com/2020/day/22
[aoc23]: http://adventofcode.com/2020/day/23
[aoc24]: http://adventofcode.com/2020/day/24
[aoc25]: http://adventofcode.com/2020/day/25
[day01]: src/Day01.kt
[day02]: src/Day02.kt
[day03]: src/Day03.kt
[day04]: src/Day04.kt





If you're stuck with Kotlin-specific questions or anything related to this template, check out the following resources:

- [Kotlin docs][docs]
- [Kotlin Slack][slack]
- Template [issue tracker][issues]


[^aoc]:
    [Advent of Code][aoc] – An annual event of Christmas-oriented programming challenges started December 2015.
    Every year since then, beginning on the first day of December, a programming puzzle is published every day for twenty-five days.
    You can solve the puzzle and provide an answer using the language of your choice.

[aoc]: https://adventofcode.com
[docs]: https://kotlinlang.org/docs/home.html
[github]: https://github.com/tbilou
[issues]: https://github.com/kotlin-hands-on/advent-of-code-kotlin-template/issues
[kotlin]: https://kotlinlang.org
[slack]: https://surveys.jetbrains.com/s3/kotlin-slack-sign-up
[template]: https://github.com/kotlin-hands-on/advent-of-code-kotlin-template
