import useDisclosure from './useDisclosure'
import { renderHook, act } from '@testing-library/react'

describe("useDisclosure", () => {
  describe('when the function open is called', () => {
    it('changes the state isOpen to true', () => {
      const hook = renderHook(useDisclosure)
      // usado pra trabalhar com estados / realizar alguma ação
      act(() => { hook.result.current.open() })
    })
    expect(hook.result.current.isOpen).toBe(true)
    expect(hook.result.current.isClosed).toBe(false)
  })
})