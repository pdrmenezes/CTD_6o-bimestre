import React from "react";
import ReactDOM from "react-dom";

const Modal = ({ isShowing, hide, title, ...props }) =>
  isShowing
    ? ReactDOM.createPortal(
        <>
          <div className="modal-overlay">
            <div className="modal-wrapper">
              <div className="modal">
                <div className="modal-header">
                  <h4>{title}</h4>
                  <button className="modal-close-button" onClick={hide}>
                    Fechar
                  </button>
                </div>
                <div className="modal-body">{props.children}</div>
              </div>
            </div>
          </div>
        </>,
        document.body
      )
    : null;

export default Modal;
