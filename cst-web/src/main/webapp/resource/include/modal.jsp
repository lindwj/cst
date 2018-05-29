<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML>
<html>
<head>
<style type='text/css'>
.inok {
	background-color: #000000;
	border-radius: 4px;
	bottom: 50%;
	color: #FFFFFF;
	left: 50%;
	margin-left: -135px;
	margin-top: -18px;
	opacity: 0.8;
	padding: 5px;
	position: fixed;
	text-align: center;
	width: 270px;
	z-index: 9999;
}
</style>
</head>
<body>

	<div class="am-modal am-modal-confirm" tabindex="-1" id="my-confirm">
		<div class="am-modal-dialog">
			<div class="am-modal-hd">确认手机号</div>
			<div class="am-modal-bd">
				亲，确定要绑定<span id="mobileNum"></span>吗？
			</div>
			<div class="am-modal-footer">
				<span class="am-modal-btn" data-am-modal-cancel>取消</span> <span
					class="am-modal-btn" data-am-modal-confirm>确定</span>
			</div>
		</div>
	</div>

	<div class="am-modal am-modal-no-btn" tabindex="-1" id="success-modal">
		<div class="am-modal-dialog">
			<div class="am-modal-hd">
				操作提示 <a href="javascript: void(0)" class="am-close am-close-spin"
					data-am-modal-close>&times;</a>
			</div>
			<div id="modal_msg_div" class="am-modal-bd">处理成功！</div>
		</div>
	</div>

	<div class="am-modal am-modal-confirm" tabindex="-1"
		id="my-confirm-delete">
		<div class="am-modal-dialog">
			<div class="am-modal-hd">确认删除</div>
			<div class="am-modal-bd">亲，再考虑一下～～</div>
			<div class="am-modal-footer">
				<span class="am-modal-btn" data-am-modal-cancel>取消</span> <span
					class="am-modal-btn" data-am-modal-confirm>确定</span>
			</div>
		</div>
	</div>

	<div class="am-modal am-modal-confirm" tabindex="-1"
		id="fillAddress-confirm">
		<div class="am-modal-dialog">
			<div class="am-modal-hd">亲，请先维护收获地址</div>
			<div class="am-modal-bd"></div>
			<div class="am-modal-footer">
				<span class="am-modal-btn" data-am-modal-cancel>取消</span> <span
					class="am-modal-btn" data-am-modal-confirm>确定</span>
			</div>
		</div>
	</div>

	<div class="am-modal am-modal-confirm" tabindex="-1"
		id="template-confirm">
		<div class="am-modal-dialog">
			<div class="am-modal-hd"></div>
			<div class="am-modal-bd"></div>
			<div class="am-modal-footer">
				<span class="am-modal-btn" data-am-modal-cancel>取消</span> <span
					class="am-modal-btn" data-am-modal-confirm>确定</span>
			</div>
		</div>
	</div>

	<div class="am-modal am-modal-loading am-modal-no-btn" tabindex="-1"
		id="my-modal-loading">
		<div class="am-modal-dialog">
			<div class="am-modal-hd">正在载入...</div>
			<div class="am-modal-bd">
				<span class="am-icon-spinner am-icon-spin"></span>
			</div>
		</div>
	</div>

	<div id="inok" class="inok" style="display: none;">
		<i class="am-text-middle"> 加入购物车成功 </i>
	</div>

</body>
</html>
