export interface ApiResponse<T>{
    data: T;
    message: string;
    timestamp: string; // ISO 8601 date format string
    statusCode: number;
    success: boolean;
}