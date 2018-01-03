package com.czwx.imall.manage.common.helper;

import com.czwx.imall.system.domain.SysUser;
import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.springframework.stereotype.Service;

/**
 * Description
 * 
 * @author Robin
 * @version V1.0
 * @createDateTime：2014-12-22
 * @Company: yqjr.com.cn
 * @Copyright: Copyright (c) 2014
 **/
@Service
public class PasswordHelper {

	private RandomNumberGenerator randomNumberGenerator = new SecureRandomNumberGenerator();

	private String algorithmName = "md5";
	private int hashIterations = 2;

	public void setRandomNumberGenerator(
			RandomNumberGenerator randomNumberGenerator) {
		this.randomNumberGenerator = randomNumberGenerator;
	}

	public void setAlgorithmName(String algorithmName) {
		this.algorithmName = algorithmName;
	}

	public void setHashIterations(int hashIterations) {
		this.hashIterations = hashIterations;
	}

	public void encryptPassword(SysUser user) {

		user.setSalt(randomNumberGenerator.nextBytes().toHex());

		String newPassword = new SimpleHash(algorithmName, user.getPassword(),
				ByteSource.Util.bytes(user.getSalt()), hashIterations).toHex();
		user.setPassword(newPassword);
	}

	/**
	 * 生成密钥
	 * @return
	 */
	public ByteSource getSalt() {

		return ByteSource.Util.bytes(randomNumberGenerator.nextBytes().toHex());
	}

	public void encryptPassword2(SysUser user) {

		// user.setSalt(randomNumberGenerator.nextBytes().toHex());

		String newPassword = new SimpleHash(algorithmName, user.getPassword(),
				ByteSource.Util.bytes(user.getSalt()), hashIterations).toHex();
		user.setPassword(newPassword);
	}

	public static void main(String[] args) {
		PasswordHelper p = new PasswordHelper();
		SysUser user = new SysUser();
		user.setPassword("123456");
		p.encryptPassword(user);
		System.out.println(user.getPassword());
		System.out.println(user.getSalt());

		/*AesCipherService aesCipherService = new AesCipherService();
		// 设置key长度
		aesCipherService.setKeySize(128);
		// 生成key
		Key key = aesCipherService.generateNewKey();
		String text = "hello";

		// 加密
		String encrptText = aesCipherService.encrypt(text.getBytes(),
				key.getEncoded()).toHex();
		System.out.println("------------------------");
		System.out.println(encrptText);
		System.out.println(key.getEncoded());
		System.out.println("------------------------");
		// 解密
		String text2 = new String(aesCipherService.decrypt(
				Hex.decode(user.getPassword()), user.getSalt().getBytes())
				.getBytes());
		Assert.assertEquals(text, text2);
		System.out.println(text2);*/
	}
}
