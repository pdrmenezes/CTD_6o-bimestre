export const createUser = (username) => {
  if (!username) throw new Error("username needed!")

  return {
    username: username
  }

}