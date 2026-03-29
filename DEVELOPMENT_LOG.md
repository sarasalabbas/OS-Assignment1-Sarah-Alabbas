# Development Log

## Instructions
Document your development process as you work on the assignment. Add entries showing:
- What you worked on
- Problems you encountered
- How you solved them
- Time spent

**Requirements**: Minimum 5 entries showing progression over time.

---

## Example Entry Format:

### Entry 1 - [April 1, 2026, 2:30 PM]
**What I did**: Forked the repository and set up my student ID

**Details**: 
- Created GitHub account with university email
- Forked the starter repository
- Changed student ID on line 92 to my actual ID (441234567)
- Compiled and ran the program successfully

**Challenges**: Had to install JDK first because javac wasn't recognized

**Solution**: Downloaded JDK 17 from Oracle website and set PATH variable

**Time spent**: 30 minutes

---

## Your Development Log:

### Entry 1 - March 29, 2026, 9:00 PM
**What I did**: Set up the project and ran the initial code 

**Details**: 
- Downloaded the starter files
- Opened the project in VS Code
- Updated my student ID in the code
- Compiled and ran the program successfully

**Challenges**: The terminal did not recognize the Java compiler at first

**Solution**: Installed JDK and made sure the PATH was configured correctly

**Time spent**: 1 hour

---

### Entry 2 - March 29, 2026, 10:30 PM
**What I did**: Implemented Feature 1 (Process Priority)

**Details**: 
- Added a priority variable to the Process class
- Generated random values between 1 and 5
- Displayed priority when processes are added to the queue

**Challenges**: Making sure the priority value is passed correctly and shown in output

**Solution**: Used a getter method and updated the print statements

**Time spent**: 20 minutes 

---

### Entry 3 - March 29, 2026, 10:50 PM
**What I did**: Implemented Feature 2 (Context Switch Counter)

**Details**: 
- Added a static counter variable
- Incremented it every time a process starts execution
- Printed the total at the end of the program

**Challenges**: Choosing the correct location to increment the counter

**Solution**: Placed it inside the scheduling loop before starting each thread

**Time spent**: 20 minutes 

---

### Entry 4 - March 29, 2026, 11:10 PM
**What I did**: Implemented Feature 3 (Waiting Time Tracking)

**Details**: 
- Added variables to track waiting time
- Used System.currentTimeMillis() to calculate waiting time
- Displayed results in a summary table

**Challenges**: Understanding when to calculate waiting time correctly

**Solution**: Tracked when processes enter the queue and when they start running

**Time spent**: 25 minutes 

---

### Entry 5 - March 29, 2026, 11:35 PM
**What I did**: Tested and verified the program

**Details**: 
- Ran the program multiple times
- Checked that all features are working correctly
- Verified output for context switches and waiting time

**Challenges**: Ensuring that the output values are reasonable

**Solution**: Carefully reviewed the logic and tested different runs

**Time spent**: 15 minutes

---

### Entry 6 - March 29, 2026, 11:50 PM
**What I did**: Completed documentation and prepared submission

**Details**: 
- Wrote answers for assignment questions
- Completed reflection file
- Organized files for submission

**Challenges**: Making sure all requirements are covered

**Solution**: Reviewed instructions and double-checked each file

**Time spent**: 30 minutes

---

## Summary

**Total time spent on assignment**: 3-4 hours

**Most challenging part**: Implementing waiting time tracking correctly

**Most interesting learning**: Understanding how CPU scheduling works in real applications

**What I would do differently next time**: Start earlier and test each feature step by step


