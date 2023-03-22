import { createUser } from "./username.js";

describe("createUser", () => {
  it("should return an object when a valid username is provided", () => {
    const result = createUser("Pedro")
  })
  it("throws an error when the parameter is invalid", () => {
    expect(() => createUser(null)).toThrow(Error)
  })
})