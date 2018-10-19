pragma solidity ^0.4.0; 
contract  add {
    uint public c;
    function Add(uint a, uint b) public returns (uint) {
        c=a+b;
        return c;
    } 
    function returnAdd() constant public returns (uint) {
        return c;
    }
}