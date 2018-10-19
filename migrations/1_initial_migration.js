var greeter = artifacts.require("./greeter.sol");

module.exports = function(deployer) {
  deployer.deploy(greeter);
};
