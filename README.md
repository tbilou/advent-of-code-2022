# advent-of-code-2022

Welcome to the Advent of Code[^aoc] Kotlin project created by [tbilou][github] using the [Advent of Code Kotlin Template][template] delivered by JetBrains.

In this repository, tbilou is about to provide solutions for the puzzles using Kotlin language.

Day | success?       | Notes
----|-------------|-------------------------------------------------
01  | [✓][day01]  | [Elves calorie counting][aoc01]. I reached the solution using a simple for loop. Later on I introduced an extension function to split a list into a list of lists.
02  | [✓][day02]  | [Rock paper scissors scoring][aoc02]. Tried to use enums and data classes but gave up and ended up hard-coding 2 truth tables 
03  | [✓][day03]  | [Rucksack Reorganization][aoc03]. The `intersect` function saved the day ;) 
04  | [✓][day04]  | [Camp Cleanup][aoc04]. The plan was to subtract the smallest "range" from the larger one and check how many items were left. If there was nothing left, then it was fully contained. For the second part I joined both lists and compared the size with a set of the same lists. 
05  | [✓][day05]  | [Supply Stacks][aoc05]. First idea was to parse the input into a list of stacks and use pop() and push() to move elements around. The hardest part was parting the input. I split the input by `\n\n` to have the initial stacks as one thing and the operations as another. For the stacks I ended up reversing the list and starting with the numbers to know how many stacks I would need to build.
06  | [✓][day06]  | [Tuning Trouble][aoc06]. Windowed for the rescue :) I ended up googling for a way find duplicates in a string and I had already done it in day #5 :facepalm:
07  | [✓][day07]  | [No Space Left On Device][aoc07]. My first idea was to replicate the commands in the actual filesystem of my computer (my creating the directories and files with the correct sizes) and use linux commands to calculate the problem :) Ended up implementing a tree of directories. After seeing a comment on the jet brains slack I implemented another version without a tree
08  | [✓][day08]  | [Treetop Tree House][aoc08] This one kicked my ass. I thought it was really clever to rotate the matrix and then check left right first and up down afterwards (by marking the visible trees with -1). At some point gave up and started again, but still with the idea to use the rotated matrix. After failing 2x I finally decided to implement the algorithm verbatim and it worked 
09  | [✓][day09]  | [Rope Bridge][aoc09] Took me forever to get my math right... I did the clever thing again for part1, where I just moved the tail to the last place where the head was. I started doing the same for the second part, not realizing that the tail doesn't move like [snake][wikipedia.snake] but there are rules and it moves diagonally. Again instead of just implementing the rules, I wanted to use vectors with magnitude and direction to calculate things. Big mistake
10  | [✓][day10]  | [Cathode-Ray Tube][aoc10] A fun one that didn't kick my ass for a change :) I did a clever thing again and converted every [addx] operation into a [noop,addx] to have a simpler simulation. I love that you have to parse the solution with your eyes (like https://adventofcode.com/2021/day/13)
11  | [✓][day11]  | [Monkey in the Middle][aco11] Pulled a Hannes and hardcoded the input. Spent some time trying to store the lambda for the operation in a variable. Again had the solution working for the example but failing for the real input. It took me some time to figure out Int was not big enough :facepalm: For the second part, tried for 1h looking at the example and checking what changed, but I was just guessing at that point. Checked subreddit for help and saw the modulo trick.
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
[day05]: src/Day05.kt
[day06]: src/Day06.kt
[day07]: src/Day07.kt
[day08]: src/Day08.kt
[day09]: src/Day09.kt
[day10]: src/Day10.kt
[day11]: src/Day11.kt





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
[wikipedia.snake]: https://en.wikipedia.org/wiki/Snake_(video_game_genre)
