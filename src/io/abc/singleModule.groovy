#!/usr/bin/groovy
package io.abc;

def run(mod) {
  build()
  deploy(mod,qa)
  test(regression)
  deploy(mod,staging)
  test(load)
  deploy(mod,prod)
}

def build() {
  mvn clean deploy -U
}

def test(name) {
  // test code goes here
}

def deploy(app,env) {
  aws cloudformation create-stack \
  --stack-name ${env}_{app)
  --parameters \
    ParameterKey=Env,ParameterValue=${env}
}

return this
