import { PaginationType } from "../pagination";

export interface LocationsResult {
  results: Location[];
  info: PaginationType;
}

export interface Location {
  id: number;
  name: string;
  type: string;
  dimension: string;
  residents: string[];
}
