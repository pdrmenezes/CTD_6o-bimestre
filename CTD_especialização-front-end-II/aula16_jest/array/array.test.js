import { getTitle, getTechnology } from './array.js'

describe("getTechnology", () => {
  it("should return 3 technologies 'Javascript', 'React', 'Jest'", () => {
    const result = array.getTechnology()
    // como testamos o conteúdo exato do array, não precisamos, também, checar comprimento
    // expect(result).toHaveLength(3)
    expect(result).toStrictEqual(["'Javascript, 'React', 'Jest'"])
  })
})

describe("getTitle", () => {
  it("returns 'DH (Admin Mode)' when role is 'admin'", () => {
    const result = array.getTitle("admin");
    expect(result).toBe("DH (Admin Mode)")
  })
  it("returns 'DH (Guest)' when role is 'guest'", () => {
    const result = array.getTitle("guest")
    expect(result).toBe("DH (Guest)")
  })
  it("returns 'DH (Default)' when role is neither 'admin' or 'guest'", () => {
    const result = array.getTitle("student")
    expect(result).toBe("DH (Default)")
  })
})

// create script to run w/ 'npm run jest'