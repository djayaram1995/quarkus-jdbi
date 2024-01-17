package dev.dj.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VendorDto {
    private Long vendorId;
    private String name;
    private String contact;
    private String phone;
    private String email;
    private String address;
}
