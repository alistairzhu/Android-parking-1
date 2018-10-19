pragma solidity ^0.4.0;

contract greeter {
  /* Define variable greeting of the type string */
  string public greeting = "HI";

  /* This runs when the contract is executed */
  function constructer() public {
    greeting = "Hello World!";
  }

  /* Main function */
  function greet(string _greeting) public returns (string) {
    greeting = _greeting;
    return greeting;
  }

  function returngreet() constant public returns (string) {
    return greeting;
  }
}