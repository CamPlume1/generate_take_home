# This is a living document showing my efforts to find the correct interpretation of the algorithm question posed by the Generate take-home challenge
Key:
- OPEN: This is an open area for me to build, explore, and test
- CLOSED: I feel like I have exhausted this line of expansion

## I am failing the code tests: why?

- Maybe special characters should be processed at the end of filtering? OPEN
- Maybe they threw in some special chars they didn't specifically allow? CLOSED
- Maybe I am interpreting encryption incorrectly? CLOSED: ZeroEncrypt
- Repeat could be intended to repeat the raw block OPEN
- Answer serialization could be messed up with list object OPEN
  - Could fix by using Jackson serialization OPEN
- Could be failing the "" case: OPEN

## What assumptions did I make that could be causing me problems?
- I am assuming valid input CLOSED
- Encryption: I am assuming a certain interpretation of 0 in encryption. CLOSED
  - Try a strategy that does not execute encrypt if the previous block **contains** a 0. CLOSED
- repeat: I repeated the value of the block after processing, instead of repeating the entire block OPEN
  - I can prove this is correct with the given test case
- I am assuming positive digits CLOSED
- I am assuming I should execute tags within a datablock as I see them
  - Add a post-processing that collects tags in a queue, and executes them at the end
