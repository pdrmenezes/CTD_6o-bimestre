import { message } from './string.js'

describe("message", () => {
  it("should return the message", () => {
    // const result = message("Pedro")
    expect(message("Pedro")).toBe('Hey Pedro!')
    expect(message("Ector")).toBe('Hey Ector!')
    expect(message("Abba")).toBe('Hey Anna!')
  })
})