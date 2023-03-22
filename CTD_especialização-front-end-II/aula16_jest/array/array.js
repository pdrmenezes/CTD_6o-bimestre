export const getTechnology = () => {
  return ["Javascript", "React", "Jest"]
}

export const getTitle = (role) => {
  if (role == "admin") {
    return "DH (Admin Mode)"
  } else if (role == "guest") {
    return "DH (Guest)"
  } else {
    return "DH (Default)"
  }
}