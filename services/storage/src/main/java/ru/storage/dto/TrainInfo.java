package ru.storage.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class TrainInfo {
    @Schema(description = "присвоенный объекту id после сохранения", example = "10")
    private int id;
    @Schema(description = "URL картинки в хранилище MinIO", example = "http://127.0.0.1:9000/train-bucket/image_b3fc8ccd-c3bf-4904-95a2-e27f817e70a3?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=minioadmin%2F20251015%2Fus-east-1%2Fs3%2Faws4_request&X-Amz-Date=20251015T195242Z&X-Amz-Expires=604800&X-Amz-SignedHeaders=host&X-Amz-Signature=89dd1ec3b734419503ddb9e1f5f4a01739cb007dd856a3b41ecc5847c11b8bf0")
    private String imageUrl;
}
