import { createContext, FC, useContext, useMemo, useState } from 'react';
import {
  AmplitudeTrackingSoftware,
  FacebookTrackingSoftware,
  GoogleTrackingSoftware
} from 'features/tracking/software';
import { TrackingSoftware, Initializable } from 'features/tracking/tracking.types';

export interface TrackingState {
  trackingSoftwares: TrackingSoftware | Initializable;
  trackEvent: (eventName: string, location: string) => void;
}

const TrackingContext = createContext<TrackingState | undefined>(undefined);

export const TrackingProvider: FC = ({ children }) => {
  const [trackingSoftwares] = useState([
    new AmplitudeTrackingSoftware(),
    new FacebookTrackingSoftware(),
    new GoogleTrackingSoftware()
  ]);

  const value = useMemo(
    () => ({
      trackingSoftwares,
      trackEvent: (eventName: string, location: string) =>
        trackingSoftwares.forEach.ty((trackingSoftware) => {
          // Why do we initialize every service ? if google doesn't require initialization
          // trackingSoftware.initialize();
          trackingSoftware.trackEvent(eventName, location);
        })
    }),
    [trackingSoftwares]
  );

  return <TrackingContext.Provider value={value}>{children}</TrackingContext.Provider>;
};

const useTracking = (): TrackingState => {
  const context = useContext(TrackingContext);
  if (!context) {
    throw new Error('useTracking must be used within a TrackingProvider');
  }
  return context;
};

export default useTracking;
